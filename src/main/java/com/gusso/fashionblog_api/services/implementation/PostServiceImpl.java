package com.gusso.fashionblog_api.services.implementation;

import com.gusso.fashionblog_api.dto.request.PostsRequestDto;
import com.gusso.fashionblog_api.dto.response.PostsResponseDto;
import com.gusso.fashionblog_api.entities.Post;
import com.gusso.fashionblog_api.entities.User;
import com.gusso.fashionblog_api.enums.Role;
import com.gusso.fashionblog_api.exceptions.CustomExceptions;
import com.gusso.fashionblog_api.repositories.PostRepository;
import com.gusso.fashionblog_api.repositories.UserRepository;
import com.gusso.fashionblog_api.services.PostServices;
import com.gusso.fashionblog_api.utils.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class PostServiceImpl implements PostServices {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final HttpSession session;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, HttpSession session) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.session = session;
    }


    @Override
    public PostsResponseDto createPost(PostsRequestDto request, String username) {


        Optional<User> user = userRepository.findByUsername(username);

        if(user.isPresent() && user.get().getRole().equals(Role.ADMINISTRATOR)){
            Post post = new Post();
            post.setUser(user.get());
            post.setTitle(request.getPostTitle());
            post.setDescription(request.getDescription());
            post.setDesignCategory(request.getCategory());
            post.setCreatedAt(LocalDateTime.now());

            Post newPost = postRepository.save(post);
//            PostsResponseDto response = new PostsResponseDto();
//            BeanUtils.copyProperties(newPost, response );

            return Mapper.createNewPost(newPost);
        }

        throw new CustomExceptions("This user cannot make a post", HttpStatus.NOT_FOUND);
    }

    @Override
    public PostsResponseDto findPostByTitle(PostsRequestDto request) {
        return null;
    }

    @Override
    public List<PostsResponseDto> findAllPosts(PostsRequestDto request) {
        return null;
    }
    @Override
    public List<PostsResponseDto> findAllPostByDesignCategory(PostsRequestDto request) {
        return null;
    }
    @Override
    public PostsResponseDto editPost(PostsRequestDto request) {
        return null;
    }

    @Override
    public PostsResponseDto deletePost(PostsRequestDto request) {
        return null;
    }
}
