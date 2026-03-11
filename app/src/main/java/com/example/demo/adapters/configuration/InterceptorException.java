package com.example.demo.adapters.configuration;

import com.example.demo.application.exception.UrlException;
import com.example.demo.application.service.UrlServiceImpl;
import com.example.demo.domain.model.dto.ServiceResponseError;
import lombok.RequiredArgsConstructor;
import org.hibernate.Interceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@RestControllerAdvice
public class InterceptorException implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(InterceptorException.class);

    private final HeadersInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(interceptor);



    }

    @ExceptionHandler(UrlException.class)
    public ResponseEntity<ServiceResponseError> handlerUrlExcption(UrlException e) {
        logger.error("UrlException: {} ", e.getMessage(), e);

        HttpStatus status = e.getStatus() != null ? e.getStatus() : HttpStatus.INTERNAL_SERVER_ERROR;

        ServiceResponseError serviceResponseError = new ServiceResponseError(status, e.getMessage());
        return ResponseEntity.status(status).body(serviceResponseError);

    }

}
