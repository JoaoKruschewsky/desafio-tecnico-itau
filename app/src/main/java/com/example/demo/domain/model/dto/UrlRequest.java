package com.example.demo.domain.model.dto;

import jakarta.validation.constraints.NotBlank;



public record UrlRequest(@NotBlank String originalUrl, String expirationDate) {
}
