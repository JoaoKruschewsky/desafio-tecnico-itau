package com.example.demo.domain.port.out;

import com.example.demo.domain.model.dto.Url;
import com.example.demo.domain.model.entity.UrlEntity;

import java.util.List;
import java.util.Optional;

public interface UrlDB {

    void saveShortUrl (Url entity);

    Optional<UrlEntity> getShortUrl (String identifierUrl);

    void patchCountClick(int countClicl, String identifierUrl);

    List<String> findAllIdentifierUrl();
}
