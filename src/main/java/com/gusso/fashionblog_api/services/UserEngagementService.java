package com.gusso.fashionblog_api.services;

import com.gusso.fashionblog_api.dto.request.UserEngagementRequestDto;
import com.gusso.fashionblog_api.dto.response.UserEngagementResponseDto;
import org.springframework.stereotype.Component;

@Component
public interface UserEngagementService {

    UserEngagementResponseDto reactToPost(UserEngagementRequestDto request, String user);
}