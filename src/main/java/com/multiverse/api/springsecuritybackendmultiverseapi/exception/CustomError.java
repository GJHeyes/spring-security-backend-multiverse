package com.multiverse.api.springsecuritybackendmultiverseapi.exception;

public class CustomError extends RuntimeException{
    public CustomError(String message) {
        super(message, new Throwable());
    }
}
