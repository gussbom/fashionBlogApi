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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServicesImpl implements CommentServices {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public CommentServicesImpl(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentResponseDto createComment(CommentRequestDto request, String username) {

        Optional<User> user = userRepository.findByUsername(username);

        Optional<Post> post = postRepository.findPostByTitle(request.getPostTitle());

        if(!post.isPresent()){
            throw new CustomExceptions("Post does not exist", HttpStatus.NOT_FOUND);
        }

        if(!user.isPresent() && user.get().getRole().equals(Role.ADMINISTRATOR)){
            user.get().setRole(Role.VISITOR);
        }

        Comment comment = new Comment();
        comment.setUser(user.get());
        comment.setPost(post.get());
        comment.setComment(request.getComment());
        comment.setCreatedAt(LocalDateTime.now());

        Comment newComment = commentRepository.save(comment);

        return Mapper.commentOnPost(newComment);
    }

    @Override
    public List<CommentResponseDto> findAllCommentsByUser(CommentRequestDto request) {
        return null;
    }

    @Override
    public CommentResponseDto findCommentByUsernameAndCommentId(CommentRequestDto request) {
        return null;
    }

    @Override
    public List<CommentResponseDto> findAllCommentsByDesignCategory(CommentRequestDto request) {
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
