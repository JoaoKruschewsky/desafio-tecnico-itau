package com.example.demo.domain.model.entity;


import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "url_short")
public class UrlEntity {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long Id;

     String idUrl;
     String shortUrl;
     String originalUrl;
     String expirationDate;
     String createdAt;

    public UrlEntity(Long id, String shortUrl, String originalUrl, String expirationDate) {
        Id = id;
        this.shortUrl = shortUrl + id;
        this.originalUrl = originalUrl;
        this.expirationDate = expirationDate;
    }

    void hourcreated(){
        createdAt = LocalDateTime.now().toString();
    }
}
