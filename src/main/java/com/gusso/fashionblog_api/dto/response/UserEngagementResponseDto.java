package com.gusso.fashionblog_api.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserEngagementResponseDto {

    private String username;
    private String postTitle;
    private LocalDateTime createdAt;

}
