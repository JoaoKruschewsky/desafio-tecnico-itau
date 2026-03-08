package com.example.demo.application.service;

import com.example.demo.adapters.out.repository.h2.repository.H2Repository;
import com.example.demo.domain.model.dto.UrlRequest;
import com.example.demo.domain.model.dto.UrlResponse;
import com.example.demo.domain.model.entity.UrlEntity;
import com.example.demo.domain.port.in.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import static com.example.demo.application.util.Constants.urlFixa;

@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {

    private final H2Repository repository;



    @Override
    public ResponseEntity<?> createUrlShort(UrlRequest body) {

        UrlEntity entity = new UrlEntity(null, urlFixa, body.originalUrl(), body.expirationDate());

        repository.saveShortUrl(entity);


        return ResponseEntity.status(201).build();




    }
}
