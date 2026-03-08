package com.example.demo.domain.port.in;

import com.example.demo.domain.model.dto.UrlRequest;
import com.example.demo.domain.model.dto.UrlResponse;
import org.springframework.http.ResponseEntity;

public interface UrlService {

    ResponseEntity<?> createUrlShort(UrlRequest body);
}
