package org.urlshort.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.urlshort.domain.data.UrlCreateRequest;
import org.urlshort.domain.data.UrlView;
import org.urlshort.services.UrlShortService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UrlShortController {
    private final UrlShortService urlShortService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/urls")
    public Page<UrlView> getUrls(@RequestParam @Min(0) @NotNull Integer page,
                                 @RequestParam @Min(1) @NotNull Integer limit){
        log.info("/urls: {page: {} limit: {}}", page, limit);
        return urlShortService.getUrls(page, limit);
    }

    @DeleteMapping("/urls/{id}")
    public void deleteUrl(@PathVariable @Min(1) @NotNull Long id) {
        log.info("/urls/{id}: {id: {}}", id);
        urlShortService.deleteUrl(id);
    }

    @PostMapping("/urls")
    public UrlView createUrl(@RequestBody UrlCreateRequest urlCreateRequest) throws JsonProcessingException {
        log.info("/urls: {urlCreateRequest: {}}", objectMapper.writeValueAsString(urlCreateRequest));
        return urlShortService.createUrl(urlCreateRequest);
    }

    @GetMapping("/{url}")
    public String getLong(@PathVariable("url") @NotBlank String shortUrl) {
        log.info("/{url}: {shortUrl: {}}", shortUrl);
        return urlShortService.getUrl(shortUrl);
    }




}
