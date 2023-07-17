package com.gusso.fashionblog_api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gusso.fashionblog_api.dto.request.UserRequestDto;
import com.gusso.fashionblog_api.dto.response.UserResponseDto;
import com.gusso.fashionblog_api.enums.Role;
import com.gusso.fashionblog_api.services.UserServices;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = UserController.class) //the purpose of defining the class is to target it during the testing.
class UserControllerTest {


//    We are testing for routing and validation.

//    The controller we're testing here is

    @MockBean
    private UserServices userServices;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    UserRequestDto requestDto;
    UserResponseDto responseDto;
    @BeforeEach
    void setUp() {
        requestDto = UserRequestDto.builder()
                .username("Johny")
                .role(Role.ADMINISTRATOR)
                .password("1234")
                .build();

        responseDto = UserResponseDto.builder()
                .username("Johny")
                .build();
    }

    @Test
    void GivenRequestData_WhenFindUser_loginUser() throws Exception {

        when(userServices.loginUser(requestDto)).thenReturn(responseDto);

        ResultActions result = mockMvc.perform(post("/app/loginUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)));

        result.andExpect(status().isOk());
    }

    @Test
    void GivenRequestData_WhenSaveData_createNewUser() throws Exception {

        when(userServices.createUser(requestDto)).thenReturn(responseDto);

        ResultActions result = mockMvc.perform(post("/app/createNewUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)));

        result.andExpect(status().isOk());
    }
}