package com.gusso.fashionblog_api.services.implementation;

import com.gusso.fashionblog_api.dto.request.UserEngagementRequestDto;
import com.gusso.fashionblog_api.dto.response.UserEngagementResponseDto;
import com.gusso.fashionblog_api.entities.Post;
import com.gusso.fashionblog_api.entities.User;
import com.gusso.fashionblog_api.entities.UserEngagement;
import com.gusso.fashionblog_api.enums.Role;
import com.gusso.fashionblog_api.exceptions.CustomExceptions;
import com.gusso.fashionblog_api.repositories.PostRepository;
import com.gusso.fashionblog_api.repositories.UserEngagementRepository;
import com.gusso.fashionblog_api.repositories.UserRepository;
import com.gusso.fashionblog_api.services.UserEngagementService;
import com.gusso.fashionblog_api.utils.Mapper;
import com.gusso.fashionblog_api.utils.UsernameGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserEngagementServiceImpl implements UserEngagementService {

    private final UserEngagementRepository userEngagementRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final HttpSession session;

    public UserEngagementServiceImpl(UserEngagementRepository userEngagementRepository, UserRepository userRepository,
                                     PostRepository postRepository, HttpSession session) {
        this.userEngagementRepository = userEngagementRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.session = session;
    }
    @Override
    public UserEngagementResponseDto reactToPost(UserEngagementRequestDto request, String username) {
        Optional<Post> post = postRepository.findPostByTitle(request.getPostTitle());
        if (!post.isPresent()) {
            throw new CustomExceptions("Post not found", HttpStatus.NOT_FOUND);
        }

        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()){
            Optional<UserEngagement> engagementOptional = userEngagementRepository.findUserEngagementByUser(user.get());

            if (engagementOptional.isPresent()){
                throw new CustomExceptions("You have reacted to this post already", HttpStatus.BAD_REQUEST);
            }
        }

        User newUser;
        if (!user.isPresent()) {
            newUser = new User();
            newUser.setPassword("0000");
            newUser.setRole(Role.VISITOR);
            newUser.setUsername(UsernameGenerator.generateRandomString(8));
            userRepository.save(newUser);
            session.setAttribute("username", newUser.getUsername());
        } else {
            newUser = user.get();
        }

        UserEngagement engagement = new UserEngagement();
        engagement.setPost(post.get());
        engagement.setReaction(request.getReaction());
        engagement.setUser(newUser);
        engagement.setCreatedAt(LocalDateTime.now());

        UserEngagement newEngagement = userEngagementRepository.save(engagement);

        return Mapper.userEngagementResponse(newEngagement);
    }
}
