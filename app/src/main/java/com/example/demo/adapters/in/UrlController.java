package com.example.demo.adapters.in;

import com.example.demo.domain.model.dto.UrlRequest;
import com.example.demo.domain.port.in.UrlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("url/v1")
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;


    @PostMapping(path = "urls")
    public ResponseEntity<?> saveUrl (@Valid @RequestBody UrlRequest urlRequest){
        return urlService.createUrlShort(urlRequest);
    }


}
