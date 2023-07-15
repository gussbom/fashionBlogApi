package com.gusso.fashionblog_api.services;

import com.gusso.fashionblog_api.dto.request.UserRequestDto;
import com.gusso.fashionblog_api.dto.response.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public interface UserServices {
    UserResponseDto createUser(UserRequestDto request);
    UserResponseDto loginUser(UserRequestDto request);
}
