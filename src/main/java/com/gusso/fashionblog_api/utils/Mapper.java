package com.gusso.fashionblog_api.mapper;

import com.gusso.fashionblog_api.dto.response.UserResponseDto;
import com.gusso.fashionblog_api.entities.User;


public class Mapper {

    public static UserResponseDto createN(User user){

        return UserResponseDto.builder()
                .username(user.getUsername())
                .build();

    }
}
