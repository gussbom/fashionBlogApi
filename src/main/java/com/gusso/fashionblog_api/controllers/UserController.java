package com.gusso.fashionblog_api.controllers;

import com.gusso.fashionblog_api.dto.request.UserRequestDto;
import com.gusso.fashionblog_api.dto.response.UserResponseDto;
import com.gusso.fashionblog_api.services.UserServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
@RestController
@RequestMapping(path="/app")
public class UserController {
    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping(path = "/createNewUser")
    public ResponseEntity<UserResponseDto> createNewUser(@RequestBody @Valid UserRequestDto request){
        return ResponseEntity.ok(userServices.createUser(request));
    }

    @PostMapping(path = "/loginUser")
    public ResponseEntity<?> loginUser(@RequestBody @Valid UserRequestDto request){
        return ResponseEntity.ok(userServices.loginUser(request));
    }

}
