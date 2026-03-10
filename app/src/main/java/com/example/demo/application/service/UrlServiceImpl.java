package com.example.demo.application.service;

import com.example.demo.adapters.out.repository.h2.repository.H2RepositoryAdapter;
import com.example.demo.application.exception.UrlException;
import com.example.demo.domain.model.dto.Url;
import com.example.demo.domain.model.dto.UrlDetailResponse;
import com.example.demo.domain.model.dto.UrlRequest;
import com.example.demo.domain.model.dto.UrlResponse;
import com.example.demo.domain.model.entity.UrlEntity;
import com.example.demo.domain.port.in.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Optional;

import static com.example.demo.application.util.Constants.urlFixa;
import static com.example.demo.application.util.ManipulationId.manipulationIdentifierUrl;

@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {

    private final H2RepositoryAdapter repository;
    private final HttpServletResponse httpServletResponse;


    private static final Logger logger = LoggerFactory.getLogger(UrlServiceImpl.class);


    @Override
    public ResponseEntity<UrlResponse> createUrlShort(UrlRequest body) {

       String urlIdentifierId = manipulationIdentifierUrl(repository.findAllIdentifierUrl());

        logger.info("Request body: {}", body);

        Url urlBuildEntity = new Url(urlFixa, body.originalUrl(), body.expirationDate(), 0, urlIdentifierId);
        UrlResponse response = new UrlResponse(urlBuildEntity.getShortUrl(), urlBuildEntity.getOriginalUrl(), urlBuildEntity.getExpirationDate());

        logger.info("Model Url buidl: " + urlBuildEntity);
        logger.info("Response: {} ", response);

        repository.saveShortUrl(urlBuildEntity);

        return ResponseEntity.ok().body(response);

    }

    @Override
    public void getUrlShort(String identifierUrl)  {
        logger.info("Identifer: {} ", identifierUrl);

        Optional<UrlEntity> urlEntity = repository.getShortUrl(identifierUrl);
        if(urlEntity.isEmpty()){
            throw new UrlException("ShortUrl not found", HttpStatus.NOT_FOUND);
        }

        logger.info("EntityUrl: {} ", urlEntity);
        logger.info("Captured click");
        logger.info("Updating count...");
        patchClickCount(urlEntity.get());

        try {
            httpServletResponse.sendRedirect(urlEntity.get().getOriginalUrl());
        } catch (IOException e) {
            throw new UrlException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



    @Override
    public ResponseEntity<UrlDetailResponse> getDetailUrlShort(String identifierUrl) {

        logger.info("Identifer: {} ", identifierUrl);

        Optional<UrlEntity> urlEntity = repository.getShortUrl(identifierUrl);

        if(urlEntity.isEmpty()){
            throw new UrlException("ShortUrl not found", HttpStatus.NOT_FOUND);
        }
        logger.info("EntityUrl: {} ", urlEntity);

        UrlDetailResponse response = new UrlDetailResponse(urlEntity.get().getShortUrl(),
                urlEntity.get().getOriginalUrl(),
                urlEntity.get().getExpirationDate(),
                urlEntity.get().getCreatedAt(), urlEntity.get().getIdentifierUrl(), urlEntity.get().getClickCount());

        logger.info("Response: {} ", response);

        return ResponseEntity.ok().body(response);

    }

    private void patchClickCount (UrlEntity urlEntity){
        int toAdd = 1;
        int clickAtualization = urlEntity.getClickCount() + 1;

        logger.info("Click uptade: {} to {}", urlEntity.getClickCount(), clickAtualization);

        logger.info("Updating click...");
        repository.patchCountClick(clickAtualization, urlEntity.getIdentifierUrl());


    }
}
