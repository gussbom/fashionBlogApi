package com.gusso.fashionblog_api.services.implementation;

import com.gusso.fashionblog_api.dto.request.CommentRequestDto;
import com.gusso.fashionblog_api.dto.response.CommentResponseDto;
import com.gusso.fashionblog_api.entities.Comment;
import com.gusso.fashionblog_api.entities.Post;
import com.gusso.fashionblog_api.entities.User;
import com.gusso.fashionblog_api.enums.Role;
import com.gusso.fashionblog_api.exceptions.CustomExceptions;
import com.gusso.fashionblog_api.repositories.CommentRepository;
import com.gusso.fashionblog_api.repositories.PostRepository;
import com.gusso.fashionblog_api.repositories.UserRepository;
import com.gusso.fashionblog_api.services.CommentServices;
import com.gusso.fashionblog_api.utils.Mapper;
import com.gusso.fashionblog_api.utils.UsernameGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServicesImpl implements CommentServices {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final HttpSession session;

    public CommentServicesImpl(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository, HttpSession session) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.session = session;
    }

    @Override
    public CommentResponseDto createComment(CommentRequestDto request, String username) {

        Optional<Post> post = postRepository.findPostByTitle(request.getPostTitle());
        if(!post.isPresent()){
            throw new CustomExceptions("Post does not exist", HttpStatus.NOT_FOUND);
        }

        Optional<User> user = userRepository.findByUsername(username);
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

        Comment comment = new Comment();
        comment.setUser(newUser);
        comment.setPost(post.get());
        comment.setComment(request.getComment());
        comment.setCreatedAt(LocalDateTime.now());

        Comment newComment = commentRepository.save(comment);

        return Mapper.commentResponse(newComment);
    }

    @Override
    public List<CommentResponseDto> findAllCommentsByDesignCategory(CommentRequestDto request) {
        return null;
    }

    @Override
    public List<CommentResponseDto> findAllCommentsByPost(CommentRequestDto request) {
        return null;
    }
    @Override
    public CommentResponseDto editCommentById(CommentRequestDto request) {
        return null;
    }

    @Override
    public CommentResponseDto deleteCommentById(CommentRequestDto request) {
        return null;
    }
}
