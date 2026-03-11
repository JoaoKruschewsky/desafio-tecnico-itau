package com.example.demo.adapters.out.repository.h2.repository;

import com.example.demo.domain.model.entity.UrlEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface H2Repository extends JpaRepository<UrlEntity, Long> {

    @Query(value = "SELECT u FROM UrlEntity u WHERE u.identifierUrl = :identifierUrl")
    Optional<UrlEntity> findByIdentifier(@Param("identifierUrl") String identifier);

    @Modifying
    @Transactional
    @Query(value = "UPDATE UrlEntity u SET u.clickCount = :countClick WHERE u.identifierUrl = :identifierUrl")
    void patchCountClick(@Param("countClick") int countClick, @Param("identifierUrl") String identifier);

    @Query(value = "SELECT identifierUrl FROM UrlEntity")
    List<String> findAllIdentifierUrl();
}
