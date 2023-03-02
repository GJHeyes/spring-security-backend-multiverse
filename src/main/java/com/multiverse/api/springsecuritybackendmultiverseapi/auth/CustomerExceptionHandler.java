package com.multiverse.api.springsecuritybackendmultiverseapi.auth;

import com.multiverse.api.springsecuritybackendmultiverseapi.exception.CustomError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerExceptionHandler {
    @ExceptionHandler(CustomError.class)
    public ResponseEntity<String> handleCustomException(CustomError e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
