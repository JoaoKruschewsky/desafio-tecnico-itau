package com.example.demo.domain.model.dto;



import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Url {

    String identifierUrl;
    String shortUrl;
    String originalUrl;
    String expirationDate;
    String createdAt;
    Integer clickCount;


    public Url( String shortUrl, String originalUrl, String expirationDate, Integer clickCount, String identifierUrl) {
        this.identifierUrl = identifierUrl;
        this.shortUrl = shortUrl + identifierUrl;
        this.originalUrl = originalUrl;
        this.expirationDate = expirationDate;
        createdAt = LocalDateTime.now().toString();
        this.clickCount = clickCount;
    }


}
