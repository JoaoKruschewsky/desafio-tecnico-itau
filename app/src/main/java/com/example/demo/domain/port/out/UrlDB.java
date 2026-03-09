package com.example.demo.domain.port.out;

import com.example.demo.domain.model.dto.Url;
import com.example.demo.domain.model.entity.UrlEntity;

import java.util.Optional;

public interface UrlDB {

    void saveShortUrl (Url entity);

    UrlEntity getShortUrl (String identifierUrl);
}
