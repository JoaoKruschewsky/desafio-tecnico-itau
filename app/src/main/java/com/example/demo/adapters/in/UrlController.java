package com.example.demo.adapters.in;

import com.example.demo.domain.model.dto.*;
import com.example.demo.domain.port.in.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("url/v1")
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @PostMapping(path = "urls")
    public ResponseEntity<UrlResponse> saveUrl (@RequestHeader(value = "x-api-key") String header, @RequestBody UrlRequest urlRequest){
        return urlService.createUrlShort(urlRequest);
    }

    @GetMapping(path = "{identifierUrl}")
    public void getUrl(@RequestHeader(value = "x-api-key") String header,  @PathVariable String identifierUrl)  {

        urlService.getUrlShort(identifierUrl);
    }

    @GetMapping(path = "urls/{identifierUrl}")
    public ResponseEntity<UrlDetailResponse> getDetailUrl(@RequestHeader(value = "x-api-key") String header, @PathVariable String identifierUrl) {
        return urlService.getDetailUrlShort(identifierUrl);
    }
    @GetMapping(path = "urls/{identifierUrl}/stats")
    public ResponseEntity<UrlStats> getStats(@RequestHeader(value = "x-api-key") String header, @PathVariable String identifierUrl) {
        return urlService.getStatistic(identifierUrl);
    }
    @GetMapping(path = "urls/{quantityUrl}/all-urls")
    public ResponseEntity<List<UrlDetailResponse>> getStats(@RequestHeader(value = "x-api-key") String header, @PathVariable Integer quantityUrl) {
        return urlService.getAllUrl(quantityUrl);
    }


}
