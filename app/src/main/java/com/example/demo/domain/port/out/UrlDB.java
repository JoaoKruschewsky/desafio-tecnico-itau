package com.example.demo.domain.port.out;

import com.example.demo.domain.model.entity.UrlEntity;

import java.util.Optional;

public interface UrlDB {

    Optional<UrlEntity> saveShortUrl (UrlEntity entity);
}
