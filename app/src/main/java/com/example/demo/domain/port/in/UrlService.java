package com.example.demo.domain.port.in;

import com.example.demo.domain.model.dto.UrlDetailResponse;
import com.example.demo.domain.model.dto.UrlRequest;
import com.example.demo.domain.model.dto.UrlResponse;
import com.example.demo.domain.model.dto.UrlStats;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface UrlService {

    ResponseEntity<UrlResponse> createUrlShort(UrlRequest body);
    void getUrlShort(String identifierUrl);
    ResponseEntity<UrlDetailResponse> getDetailUrlShort(String identifierUrl);
    ResponseEntity<UrlStats> getStatistic(String identifierUrl);
    ResponseEntity<List<UrlDetailResponse>> getAllUrl(Integer quantityUnit);


}
