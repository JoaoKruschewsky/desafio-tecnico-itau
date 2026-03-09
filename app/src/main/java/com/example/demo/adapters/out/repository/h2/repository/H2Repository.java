package com.example.demo.adapters.out.repository.h2.repository;

import com.example.demo.domain.model.entity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface H2Repository extends JpaRepository<UrlEntity, Long> {

    @Query(value = "SELECT u FROM UrlEntity u WHERE u.identifierUrl = :identifierUrl")
    Optional<UrlEntity> findByIdentifier(@Param("identifierUrl") String identifier);
}
