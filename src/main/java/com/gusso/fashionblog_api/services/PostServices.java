package com.gusso.fashionblog_api.services;

import com.gusso.fashionblog_api.dto.request.PostsRequestDto;
import com.gusso.fashionblog_api.dto.response.PostsResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PostServices {

    PostsResponseDto createPost(PostsRequestDto request, String username);

    PostsResponseDto findPostByTitle(PostsRequestDto request);

    List<PostsResponseDto> findAllPosts(PostsRequestDto request);

    List<PostsResponseDto> findAllPostByDesignCategory(PostsRequestDto request);

    PostsResponseDto editPost(PostsRequestDto request);

    PostsResponseDto deletePost(PostsRequestDto request);
}
