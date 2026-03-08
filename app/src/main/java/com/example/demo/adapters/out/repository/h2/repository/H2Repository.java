package com.example.demo.adapters.out.repository.h2.repository;

import com.example.demo.domain.model.entity.UrlEntity;
import com.example.demo.domain.port.out.UrlDB;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class H2Repository implements UrlDB {

    private final JpaRepository<UrlEntity, Long> repository;

    @Override
    public Optional<UrlEntity> saveShortUrl(UrlEntity entity) {

        repository.save(entity);
        return Optional.empty();
    }
}
