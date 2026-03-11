package com.example.demo.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

public record ServiceResponseError(
        @JsonProperty("StatusCode")HttpStatus status,
        @JsonProperty("Message") String message
        ) {
}
