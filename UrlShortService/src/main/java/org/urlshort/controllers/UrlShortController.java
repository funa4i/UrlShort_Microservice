package org.urlshort.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
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
    public Page<UrlView> getUrls(@RequestParam Integer page,
                                    @RequestParam Integer limit){
        return urlShortService.getUrls(page, limit);
    }

    @DeleteMapping("/url/{id}")
    public void deleteUrl(@PathVariable Long id) {
        urlShortService.deleteUrl(id);
    }

    @PostMapping("/url")
    public UrlView createUrl(@RequestBody UrlCreateRequest url){
        return urlShortService.createUrl(url);
    }

    @GetMapping("/{url}")
    public ResponseEntity<String> getLong(@PathVariable("url") String shortUrl) throws JsonProcessingException {

        var url = urlShortService.getUrl(shortUrl);

        return ResponseEntity.status(303).header("Location", url).body("");

    }




}
