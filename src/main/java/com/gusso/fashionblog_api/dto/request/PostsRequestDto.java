package com.gusso.fashionblog_api.dto.request;

import com.gusso.fashionblog_api.enums.DesignCategory;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class PostsRequestDto {

    @NotBlank
    private DesignCategory category;

    @NotBlank
    private String postTitle;

    @NotBlank
    private String description;
}
