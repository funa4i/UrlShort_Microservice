package org.urlshort.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.urlshort.domain.data.UrlCreateRequest;
import org.urlshort.domain.data.UrlView;
import org.urlshort.services.UrlShortService;

@RestController
@RequiredArgsConstructor
public class UrlShortController {

    private final UrlShortService urlShortService;

    @GetMapping("/urls")
    public Page<UrlView> getUrls(@RequestParam @Min(0) @NotNull Integer page,
                                 @RequestParam @Min(1) @NotNull Integer limit){
        return urlShortService.getUrls(page, limit);
    }

    @DeleteMapping("/urls/{id}")
    public void deleteUrl(@PathVariable @Min(1) @NotNull Long id) {
        urlShortService.deleteUrl(id);
    }

    @PostMapping("/urls")
    public UrlView createUrl(@RequestBody UrlCreateRequest url){
        return urlShortService.createUrl(url);
    }

    @GetMapping("/{url}")
    public String getLong(@PathVariable("url") @NotBlank String shortUrl) {
        return urlShortService.getUrl(shortUrl);
    }




}
