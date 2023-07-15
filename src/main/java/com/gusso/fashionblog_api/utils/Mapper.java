package com.gusso.fashionblog_api.utils;

import com.gusso.fashionblog_api.dto.response.CommentResponseDto;
import com.gusso.fashionblog_api.dto.response.PostsResponseDto;
import com.gusso.fashionblog_api.dto.response.UserEngagementResponseDto;
import com.gusso.fashionblog_api.dto.response.UserResponseDto;
import com.gusso.fashionblog_api.entities.Comment;
import com.gusso.fashionblog_api.entities.Post;
import com.gusso.fashionblog_api.entities.User;
import com.gusso.fashionblog_api.entities.UserEngagement;


public class Mapper {
    public static UserResponseDto createNewUser(User user){
        return UserResponseDto.builder()
                .username(user.getUsername())
                .build();
    }
    public static UserResponseDto loginUser(User user){
        return UserResponseDto.builder()
                .username(user.getUsername())
                .build();
    }
    public static PostsResponseDto createNewPost(Post post){
        return PostsResponseDto.builder()
                .username(post.getUser().getUsername())
                .postTitle(post.getTitle())
                .createdAt(post.getCreatedAt())
                .build();
    }
    public static CommentResponseDto commentOnPost(Comment comment){
        return CommentResponseDto.builder()
                .username(comment.getUser().getUsername())
                .comment(comment.getComment())
                .postTitle(comment.getPost().getTitle())
                .createdAt(comment.getCreatedAt())
                .build();
    }
    public static UserEngagementResponseDto reactToPost(UserEngagement engagement){
        return UserEngagementResponseDto.builder()
                .username(engagement.getUser().getUsername())
                .postTitle(engagement.getPost().getTitle())
                .build();
    }
}
