package com.example.demo.application.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class UrlException extends RuntimeException{

    private final HttpStatus status;

    public UrlException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

}
