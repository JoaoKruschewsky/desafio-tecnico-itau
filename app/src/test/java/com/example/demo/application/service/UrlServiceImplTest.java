package com.example.demo.application.service;

import com.example.demo.adapters.out.repository.h2.repository.H2Repository;
import com.example.demo.adapters.out.repository.h2.repository.H2RepositoryAdapter;
import com.example.demo.application.exception.UrlException;
import com.example.demo.domain.model.dto.Url;
import com.example.demo.domain.model.dto.UrlDetailResponse;
import com.example.demo.domain.model.dto.UrlRequest;
import com.example.demo.domain.model.dto.UrlResponse;
import com.example.demo.domain.model.entity.UrlEntity;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
class UrlServiceImplTest {


    @Mock
    private H2RepositoryAdapter repository;
    @Mock
    private HttpServletResponse response;
    @Mock
    private ResponseEntity<UrlResponse> urlResponse;


    @InjectMocks
    private UrlServiceImpl service;

    private UrlRequest makeUrlRequest() {
        return new UrlRequest("https://www.itau.com.br/minha-conta/saldo", "2024-12-31T23:59:59Z");
    }


    @Test
    void createUrlShort() {


        List<String> listIdentifier = new ArrayList<>();
        when(repository.findAllIdentifierUrl()).thenReturn(listIdentifier);
        doNothing().when(repository).saveShortUrl(any(Url.class));

        ResponseEntity<UrlResponse> checkResponse = service.createUrlShort(makeUrlRequest());

        assertNotNull(response);
        assertEquals(200, checkResponse.getStatusCode().value());

    }

    @Test
    void getUrlShort() throws IOException {

        UrlEntity mockEntity = mock(UrlEntity.class);


        when(repository.getShortUrl(any())).thenReturn(Optional.of(mockEntity));
        service.getUrlShort(mockEntity.getIdentifierUrl());

        verify(repository, times(1).description("Get short called")).getShortUrl(any());
        verify(repository).patchCountClick(1, mockEntity.getIdentifierUrl());
        verify(response).sendRedirect(mockEntity.getShortUrl());
    }

    @Test
    void getDetailUrlShort() {

        when(repository.getShortUrl(any())).thenReturn(Optional.of(mock(UrlEntity.class)));
        ResponseEntity<UrlDetailResponse> response = service.getDetailUrlShort(any());

        UrlDetailResponse responseDetail = response.getBody();

        assertNotNull(responseDetail);
        assertEquals(UrlDetailResponse.class, responseDetail.getClass());
        assertEquals(200, response.getStatusCode().value());

    }
    @Test
    void NotFoundUrlShort() {

        when(repository.getShortUrl(any())).thenReturn(Optional.empty());

        try {
            service.getDetailUrlShort(any());

        } catch (UrlException e) {
            assertEquals(UrlException.class, e.getClass());
        }

    }
}