package com.example.demo.adapters.in;

import com.example.demo.domain.model.dto.UrlDetailResponse;
import com.example.demo.domain.model.dto.UrlRequest;
import com.example.demo.domain.model.dto.UrlResponse;
import com.example.demo.domain.port.in.UrlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("url/v1")
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;


    @PostMapping(path = "urls")
    public ResponseEntity<UrlResponse> saveUrl (@Valid @RequestBody UrlRequest urlRequest){
        return urlService.createUrlShort(urlRequest);
    }

    @GetMapping(path = "{identifierUrl}")
    public ResponseEntity<UrlResponse> getUrl(@Valid @PathVariable String identifierUrl) {
        return urlService.getUrlShort(identifierUrl);
    }

    @GetMapping(path = "urls/{identifierUrl}")
    public ResponseEntity<UrlDetailResponse> getDetailUrl(@Valid @PathVariable String identifierUrl) {
        return urlService.getDetailUrlShort(identifierUrl);
    }


}
