package com.gusso.fashionblog_api.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class CustomExceptions extends RuntimeException{

    private String message;

    private HttpStatus status;

    private final LocalDateTime time = LocalDateTime.now();


    public CustomExceptions(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
