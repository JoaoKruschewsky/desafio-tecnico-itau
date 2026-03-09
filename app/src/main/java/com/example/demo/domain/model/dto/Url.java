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

    public Url( String shortUrl, String originalUrl, String expirationDate) {
        identifierUrl = "url" + generateId();
        this.shortUrl = shortUrl + identifierUrl;
        this.originalUrl = originalUrl;
        this.expirationDate = expirationDate;
        createdAt = LocalDateTime.now().toString();;
    }


    private String generateId() {
        int i = 1;
        return String.valueOf(i++);
    }
}
