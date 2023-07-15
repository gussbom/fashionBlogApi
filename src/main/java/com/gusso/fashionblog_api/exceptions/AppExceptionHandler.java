package com.gusso.activitytrackerapp.exceptions;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(CustomExceptions.class)
    public ResponseEntity<?> ex1(CustomExceptions customExceptions){
        return ResponseEntity.badRequest().body(customExceptions.getLocalizedMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> handleGlobalExceptions(MethodArgumentNotValidException ex,
                                                       WebRequest request) {
        String[] errors = ex.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toArray(String[]::new);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
