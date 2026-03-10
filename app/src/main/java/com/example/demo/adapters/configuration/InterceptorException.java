package com.example.demo.adapters.configuration;

import com.example.demo.application.exception.UrlException;
import com.example.demo.application.service.UrlServiceImpl;
import com.example.demo.domain.model.dto.ServiceResponseError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class InterceptorException {

    private static final Logger logger = LoggerFactory.getLogger(InterceptorException.class);


    @ExceptionHandler(UrlException.class)
    public ResponseEntity<ServiceResponseError> handlerUrlExcption(UrlException e) {
        logger.error("UrlException: {} ", e.getMessage(), e);

        HttpStatus status = e.getStatus() != null ? e.getStatus() : HttpStatus.INTERNAL_SERVER_ERROR;

        ServiceResponseError serviceResponseError = new ServiceResponseError(status, e.getMessage());
        return ResponseEntity.status(status).body(serviceResponseError);

    }

}
