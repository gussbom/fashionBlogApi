package com.gusso.fashionblog_api.services;

import com.gusso.fashionblog_api.dto.request.CommentRequestDto;
import com.gusso.fashionblog_api.dto.response.CommentResponseDto;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CommentServices {

    CommentResponseDto createComment(CommentRequestDto request, String user);

    List<CommentResponseDto> findAllCommentsByUser(CommentRequestDto request);

    CommentResponseDto findCommentByUsernameAndCommentId(CommentRequestDto request);

    List<CommentResponseDto> findAllCommentsByDesignCategory(CommentRequestDto request);

    CommentResponseDto editCommentById(CommentRequestDto request);

    CommentResponseDto deleteCommentById(CommentRequestDto request);
}
