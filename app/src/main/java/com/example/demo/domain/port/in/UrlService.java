package com.example.demo.domain.port.in;

import com.example.demo.domain.model.dto.UrlDetailResponse;
import com.example.demo.domain.model.dto.UrlRequest;
import com.example.demo.domain.model.dto.UrlResponse;
import org.springframework.http.ResponseEntity;

public interface UrlService {

    ResponseEntity<UrlResponse> createUrlShort(UrlRequest body);
    ResponseEntity<UrlResponse> getUrlShort(String identifierUrl);
    ResponseEntity<UrlDetailResponse> getDetailUrlShort(String identifierUrl);


}
