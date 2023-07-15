package com.gusso.fashionblog_api.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PostsResponseDto {
    private String username;
    private String postTitle;
    private LocalDateTime createdAt;
}
