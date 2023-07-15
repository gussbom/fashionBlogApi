package com.gusso.fashionblog_api.dto.response;

import com.gusso.fashionblog_api.enums.Reaction;
import com.gusso.fashionblog_api.enums.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentResponseDto {

    private String username;
    private String postTitle;
    private String comment;
    private LocalDateTime createdAt;

}
