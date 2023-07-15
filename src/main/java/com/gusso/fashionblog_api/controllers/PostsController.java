package com.gusso.fashionblog_api.controllers;

import com.gusso.fashionblog_api.dto.request.PostsRequestDto;
import com.gusso.fashionblog_api.dto.response.PostsResponseDto;
import com.gusso.fashionblog_api.services.CommentServices;
import com.gusso.fashionblog_api.services.PostServices;
import com.gusso.fashionblog_api.services.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(path="/app")
public class PostsController {

    private final PostServices postServices;
    private final HttpSession session;

    public PostsController(PostServices postServices, HttpSession session) {
        this.postServices = postServices;
        this.session = session;
    }

    @PostMapping(path="/createPost")
    public ResponseEntity<PostsResponseDto> createPost(@RequestBody PostsRequestDto request) {
        return ResponseEntity.ok(postServices.createPost(request, (String) session.getAttribute("username")));
    }

//    public ResponseEntity<?> createPost(@RequestBody PostsRequestDto request){
//        PostsResponseDto post = postServices.createPost(request, session.getAttribute("username"));
//        return new ResponseEntity<>(post, HttpStatus.OK);
}
