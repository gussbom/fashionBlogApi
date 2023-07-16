package com.gusso.fashionblog_api.dto.response;

import com.gusso.fashionblog_api.enums.DesignCategory;
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
    private String postDescription;
    private DesignCategory category;
    private LocalDateTime createdAt;
}
