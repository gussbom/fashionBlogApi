package com.gusso.fashionblog_api.services;

import com.gusso.fashionblog_api.dto.request.PostsRequestDto;
import com.gusso.fashionblog_api.dto.response.PostsResponseDto;
import com.gusso.fashionblog_api.entities.Post;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PostServices {

    PostsResponseDto createPost(PostsRequestDto request, String username);

    PostsResponseDto findPostByTitle(PostsRequestDto request);

    List<PostsResponseDto> findAllPosts(String username);

    List<PostsResponseDto> findAllPostByDesignCategory(PostsRequestDto request, String username);

    PostsResponseDto editPostByTitle(PostsRequestDto request, String username);

    String deletePostByTitle(PostsRequestDto request, String username);
}
