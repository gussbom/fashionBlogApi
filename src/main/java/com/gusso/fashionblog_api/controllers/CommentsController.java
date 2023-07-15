package com.gusso.fashionblog_api.controllers;

import com.gusso.fashionblog_api.dto.request.CommentRequestDto;
import com.gusso.fashionblog_api.dto.response.CommentResponseDto;
import com.gusso.fashionblog_api.services.CommentServices;
import com.gusso.fashionblog_api.services.PostServices;
import com.gusso.fashionblog_api.services.UserServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(path="/app")
public class CommentsController {

    private final CommentServices commentServices;
    private final HttpSession session;

    public CommentsController(CommentServices commentServices, HttpSession session) {
        this.commentServices = commentServices;
        this.session = session;
    }

    @PostMapping(path="/createComment")
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentRequestDto request){
        return ResponseEntity.ok(commentServices.createComment(request, (String) session.getAttribute("username")));
    }
}
