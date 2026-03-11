package com.example.demo.adapters.configuration;

import com.example.demo.application.exception.UrlException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;

import static com.example.demo.application.util.Constants.x_api_key;

@Component
public class HeadersInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        if ( !request.getHeader("x-api-key").equals(x_api_key)) {
            throw  new UrlException("header x-api-key unauthorized", HttpStatus.UNAUTHORIZED);
        }

        if (request.getHeader("x-api-key").isEmpty()) {
            throw new UrlException("header x-api-key empty", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return true;
    }
}
