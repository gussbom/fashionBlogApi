package com.gusso.fashionblog_api.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gusso.fashionblog_api.dto.request.PostsRequestDto;
import com.gusso.fashionblog_api.dto.response.PostsResponseDto;
import com.gusso.fashionblog_api.enums.DesignCategory;
import com.gusso.fashionblog_api.services.PostServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.servlet.http.HttpSession;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = PostsController.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
class PostsControllerTest {

    @MockBean
    private PostServices postServices;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    PostsRequestDto requestDto;
    PostsResponseDto responseDto;

    @BeforeEach
    void setUp() {

        requestDto = PostsRequestDto.builder()
                .postTitle("Post title")
                .description("Description")
                .category(DesignCategory.ACCESSORIES)
                .build();

        responseDto = PostsResponseDto.builder()
                .username("Jolly")
                .postTitle("Post title")
                .postDescription("Description")
                .category(DesignCategory.ACCESSORIES)
                .createdAt(LocalDateTime.now())
                .build();

        String postDeleted = "Post deleted";
    }

    @Test
    void GivenRequestDto_WhenValidateUser_WhenSavePost_ReturnResponse() throws Exception {

        String userInSession = "Jolly";
        when(postServices.createPost(requestDto, userInSession)).thenReturn(responseDto);

        ResultActions result = mockMvc.perform(post("/app/createPost")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)));

        result.andExpect(status().isOk());
    }

    @Test
    void GivenRequestData_WhenValidateUser_WhenFindPost_deletePost() throws Exception {

        String userInSession = "Jolly";
        when(postServices.createPost(requestDto, userInSession)).thenReturn(responseDto);

        ResultActions result = mockMvc.perform(delete("/app/deletePost")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)));

        result.andExpect(status().isOk());
    }

    @Test
    void findPostByTitle() {
    }

    @Test
    void findAllPost() {
    }

    @Test
    void findAllPostsByDesignCategory() {
    }
}