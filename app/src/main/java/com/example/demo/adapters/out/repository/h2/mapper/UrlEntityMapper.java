package com.example.demo.adapters.out.repository.h2.mapper;

import com.example.demo.domain.model.dto.Url;
import com.example.demo.domain.model.entity.UrlEntity;

public class UrlEntityMapper {



    public static UrlEntity UrlToMapperUrlEntity(Url model) {

        UrlEntity modelToEntity = new UrlEntity();
        modelToEntity.setIdentifierUrl(model.getIdentifierUrl());
        modelToEntity.setShortUrl(model.getShortUrl());
        modelToEntity.setOriginalUrl(model.getOriginalUrl());
        modelToEntity.setExpirationDate(model.getExpirationDate());
        modelToEntity.setCreatedAt(model.getCreatedAt());

        return modelToEntity;

    }
}
