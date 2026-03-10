package com.example.demo.adapters.out.repository.h2.repository;

import com.example.demo.application.exception.UrlException;
import com.example.demo.application.service.UrlServiceImpl;
import com.example.demo.domain.model.dto.Url;
import com.example.demo.domain.model.entity.UrlEntity;
import com.example.demo.domain.port.out.UrlDB;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static com.example.demo.adapters.out.repository.h2.mapper.UrlEntityMapper.UrlToMapperUrlEntity;

@Component
@RequiredArgsConstructor
public class H2RepositoryAdapter implements UrlDB {

    private static final Logger logger = Logger.getLogger(H2RepositoryAdapter.class.getName());

    private final H2Repository h2Repository;

    @Override
    public void saveShortUrl(Url entityModel) {
        UrlEntity entity = UrlToMapperUrlEntity(entityModel);
        logger.info("Mapper entity result: " + entity);
        h2Repository.save(entity);
    }

    @Override
    public Optional<UrlEntity> getShortUrl( String identifierUrl) {

        return  h2Repository.findByIdentifier(identifierUrl);

    }

    @Override
    public void patchCountClick(int countClick, String identifierUrl)  {
        h2Repository.patchCountClick(countClick, identifierUrl);
    }

    @Override
    public List<String> findAllIdentifierUrl() {
        return h2Repository.findAllIdentifierUrl();
    }

}
