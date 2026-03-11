package com.example.demo.domain.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;

@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public record UrlRequest(@NotBlank String originalUrl, String expirationDate) {
}
