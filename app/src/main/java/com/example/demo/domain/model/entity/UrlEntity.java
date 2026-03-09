package com.example.demo.domain.model.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "url_short")
@Data
public class UrlEntity {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long Id;

     String identifierUrl;
     String shortUrl;
     String originalUrl;
     String expirationDate;
     String createdAt;

}
