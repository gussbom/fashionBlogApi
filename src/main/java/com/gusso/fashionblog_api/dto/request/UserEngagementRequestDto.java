package com.gusso.fashionblog_api.dto.request;

import com.gusso.fashionblog_api.enums.Reaction;
import lombok.Data;

@Data
public class UserEngagementRequestDto {

    private Reaction reaction;
    private String postTitle;
    private String user;
}
