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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostServices {

    private final PostRepository postRepository;
    private final UserRepository userRepository;


    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }
    @Override
    public PostsResponseDto createPost(PostsRequestDto request, String username) {

        Optional<User> user = userRepository.findByUsername(username);

        if(!user.isPresent() && user.get().getRole().equals(Role.ADMINISTRATOR)){
            throw new CustomExceptions("This user cannot make a post", HttpStatus.NOT_FOUND);
        }

        Optional<Post> checkIfPostTitle = postRepository.findPostByTitle(request.getPostTitle());
        if(checkIfPostTitle.isPresent()){
            throw new CustomExceptions("Post title exists already. Choose another.", HttpStatus.CONFLICT);
        }

        Post post = new Post();
        post.setUser(user.get());
        post.setTitle(request.getPostTitle());
        post.setDescription(request.getDescription());
        post.setDesignCategory(request.getCategory());
        post.setCreatedAt(LocalDateTime.now());

        Post newPost = postRepository.save(post);
//            PostsResponseDto response = new PostsResponseDto();
//            BeanUtils.copyProperties(newPost, response );

        return Mapper.postResponse(newPost);


    }
    @Override
    public PostsResponseDto findPostByTitle(PostsRequestDto request) {

        Optional<Post> post = postRepository.findPostByTitle(request.getPostTitle());
        if(!post.isPresent()){
            throw new CustomExceptions("Post does not exist", HttpStatus.NOT_FOUND);
        }

        return Mapper.postResponse(post.get());
    }
    @Override
    public List<PostsResponseDto> findAllPosts(String username) {


        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent() && userOptional.get().getRole().equals(Role.ADMINISTRATOR)) {
            throw new CustomExceptions("You're not an Admin", HttpStatus.NOT_FOUND);
        }

        List<Post> post = postRepository.findAllByUser(userOptional.get());

        List<PostsResponseDto> response = post.stream()
                .map(posts -> PostsResponseDto.builder()
                        .username(posts.getUser().getUsername())
                        .postTitle(posts.getTitle())
                        .postDescription(posts.getDescription())
                        .category(posts.getDesignCategory())
                        .createdAt(posts.getCreatedAt())
                        .build())
                .collect(Collectors.toList());

        return response;
    }
    @Override
    public List<PostsResponseDto> findAllPostByDesignCategory(PostsRequestDto request, String username) {


        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent() && userOptional.get().getRole().equals(Role.ADMINISTRATOR)) {
            throw new CustomExceptions("You're not an Admin", HttpStatus.NOT_FOUND);
        }

        List<Post> post = postRepository.findAllByDesignCategory(request.getCategory());

        List<PostsResponseDto> response = post.stream()
                .map(posts -> PostsResponseDto.builder()
                        .username(posts.getUser().getUsername())
                        .postTitle(posts.getTitle())
                        .postDescription(posts.getDescription())
                        .category(posts.getDesignCategory())
                        .createdAt(posts.getCreatedAt())
                        .build())
                .collect(Collectors.toList());

        return response;
    }
    @Override
    public PostsResponseDto editPostByTitle(PostsRequestDto request, String username) {

        Optional<Post> post = postRepository.findPostByTitle(request.getPostTitle());
        if(!post.isPresent()){
            throw new CustomExceptions("Post does not exist", HttpStatus.NOT_FOUND);
        }

        Optional<User> user = userRepository.findByUsername(username);
        if(!user.isPresent() && user.get().getRole().equals(Role.ADMINISTRATOR)){
           throw new CustomExceptions("You're not an Admin", HttpStatus.NOT_FOUND);
        }

        return null;
    }
    @Override
    public String deletePostByTitle(PostsRequestDto request, String username) {

        Optional<Post> post = postRepository.findPostByTitle(request.getPostTitle());
        if(!post.isPresent()){
            throw new CustomExceptions("Post does not exist", HttpStatus.NOT_FOUND);
        }

        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent() && user.get().getRole().equals(Role.ADMINISTRATOR)){
            postRepository.delete(post.get());
            return "Post deleted";
        }

        throw new CustomExceptions("You are not authorized to delete this post", HttpStatus.BAD_REQUEST);
    }
}
