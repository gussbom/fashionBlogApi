package com.gusso.fashionblog_api.controllers;

import com.gusso.fashionblog_api.dto.request.PostsRequestDto;
import com.gusso.fashionblog_api.dto.response.PostsResponseDto;
import com.gusso.fashionblog_api.services.PostServices;
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

    @DeleteMapping(path="/deletePost")
    ResponseEntity<?> deletePost(@RequestBody PostsRequestDto request){
        return ResponseEntity.ok(postServices.deletePostByTitle(request, (String)session.getAttribute("username")));
    }

    @GetMapping(path="/findPostByTitle")
    ResponseEntity<?> findPostByTitle(@RequestBody PostsRequestDto request){
        return ResponseEntity.ok(postServices.findPostByTitle(request));
    }

    @GetMapping(path="/findAllPosts")
    ResponseEntity<?> findAllPost(){
        String username = (String) session.getAttribute("username");
        return ResponseEntity.ok(postServices.findAllPosts(username));
    }

    @GetMapping(path="/findAllPostsByDesignCategory")
    ResponseEntity<?> findAllPostsByDesignCategory(@RequestBody PostsRequestDto request){
        return ResponseEntity.ok(postServices.findAllPostByDesignCategory(request, (String)session.getAttribute("username")));
    }

    @PatchMapping(path="/editPostByTitle")
    ResponseEntity<?> editPostByTitle(@RequestBody PostsRequestDto request){
        return ResponseEntity.ok(postServices.editPostByTitle(request, (String)session.getAttribute("username")));
    }

//    public ResponseEntity<?> createPost(@RequestBody PostsRequestDto request){
//        PostsResponseDto post = postServices.createPost(request, session.getAttribute("username"));
//        return new ResponseEntity<>(post, HttpStatus.OK);
}
