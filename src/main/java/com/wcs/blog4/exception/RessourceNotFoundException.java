package com.wcs.blog4.exception;

public class RessourceNotFoundException extends RuntimeException{
    public RessourceNotFoundException(String message) {
        super(message);
    }
}
