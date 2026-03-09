package com.example.demo.application.exception;

import org.springframework.http.HttpStatus;


public class UrlException extends RuntimeException{

    private final HttpStatus status;

    public UrlException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
