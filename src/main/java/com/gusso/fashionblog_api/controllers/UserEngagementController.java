package com.gusso.fashionblog_api.controllers;

import com.gusso.fashionblog_api.dto.request.UserEngagementRequestDto;
import com.gusso.fashionblog_api.dto.response.UserEngagementResponseDto;
import com.gusso.fashionblog_api.services.UserEngagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(path="/app")
public class UserEngagementController {

    private final UserEngagementService userEngagementService;
    private final HttpSession session;

    public UserEngagementController(UserEngagementService userEngagementService, HttpSession session) {
        this.userEngagementService = userEngagementService;
        this.session = session;
    }

    @PostMapping(path="/reactToPost")
    ResponseEntity<UserEngagementResponseDto> reactToPost(@RequestBody UserEngagementRequestDto request){
        return ResponseEntity.ok(userEngagementService.reactToPost(request, (String)session.getAttribute("username")));
    }
}
