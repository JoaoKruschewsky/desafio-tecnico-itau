package com.example.demo.domain.model.dto;

public record UrlDetailResponse(String shortUrl, String originalUrl, String expirationDate, String createdAt, String identifierUrl) {
}
