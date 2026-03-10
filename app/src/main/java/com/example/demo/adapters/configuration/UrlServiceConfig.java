package com.example.demo.adapters.configuration;

import com.example.demo.adapters.out.repository.h2.repository.H2Repository;
import com.example.demo.adapters.out.repository.h2.repository.H2RepositoryAdapter;
import com.example.demo.application.service.UrlServiceImpl;
import com.example.demo.domain.port.in.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UrlServiceConfig {

    @Bean
    public UrlService UrlService(H2RepositoryAdapter repository, HttpServletResponse httpServletResponse){
        return new UrlServiceImpl(repository, httpServletResponse);
    }

}
