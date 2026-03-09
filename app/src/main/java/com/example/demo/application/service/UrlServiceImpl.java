package com.example.demo.application.service;

import com.example.demo.adapters.out.repository.h2.repository.H2RepositoryAdapter;
import com.example.demo.application.exception.UrlException;
import com.example.demo.domain.model.dto.Url;
import com.example.demo.domain.model.dto.UrlDetailResponse;
import com.example.demo.domain.model.dto.UrlRequest;
import com.example.demo.domain.model.dto.UrlResponse;
import com.example.demo.domain.model.entity.UrlEntity;
import com.example.demo.domain.port.in.UrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.logging.Logger;

import static com.example.demo.application.util.Constants.urlFixa;

@Slf4j
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {

    private final H2RepositoryAdapter repository;

    private static final Logger logger = Logger.getLogger(UrlServiceImpl.class.getName());


    @Override
    public ResponseEntity<UrlResponse> createUrlShort(UrlRequest body) {

        logger.info("Request body: " +  body );

        Url urlBuildEntity = new Url(urlFixa, body.originalUrl(), body.expirationDate());
        UrlResponse response = new UrlResponse(urlBuildEntity.getShortUrl(), urlBuildEntity.getOriginalUrl(), urlBuildEntity.getExpirationDate());

        logger.info("Model Url buidl: " + urlBuildEntity);
        logger.info("Response: " + response);

        repository.saveShortUrl(urlBuildEntity);

        return ResponseEntity.ok().body(response);

    }

    @Override
    public ResponseEntity<UrlResponse> getUrlShort(String identifierUrl) {

        logger.info("Identifer: " + identifierUrl);

        UrlEntity urlEntity = repository.getShortUrl(identifierUrl);


        logger.info("EntityUrl: " + urlEntity);

        UrlResponse response = new UrlResponse(urlEntity.getShortUrl(),
                urlEntity.getOriginalUrl(),
                urlEntity.getExpirationDate());

        logger.info("Response: " + response);

        return ResponseEntity.ok().body(response);
    }



    @Override
    public ResponseEntity<UrlDetailResponse> getDetailUrlShort(String identifierUrl) {

        logger.info("Identifer: " + identifierUrl);

        UrlEntity urlEntity = repository.getShortUrl(identifierUrl);


        logger.info("EntityUrl: " + urlEntity);

        UrlDetailResponse response = new UrlDetailResponse(urlEntity.getShortUrl(),
                urlEntity.getOriginalUrl(),
                urlEntity.getExpirationDate(),
                urlEntity.getCreatedAt(), urlEntity.getIdentifierUrl());

        logger.info("Response: " + response);

        return ResponseEntity.ok().body(response);

    }
}
