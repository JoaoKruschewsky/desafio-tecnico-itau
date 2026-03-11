package com.example.demo.domain.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public record UrlDetailResponse(String shortUrl, String originalUrl, String expirationDate, String createdAt, String identifierUrl, Integer countClick) {
}
