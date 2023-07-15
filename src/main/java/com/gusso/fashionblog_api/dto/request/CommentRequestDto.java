package com.gusso.fashionblog_api.dto.request;

import lombok.Data;

@Data
public class CommentRequestDto {

    private String postTitle;
    private String comment;
}
