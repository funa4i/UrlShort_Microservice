package org.urlshort.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.urlshort.feign.data.UrlCreateRequest;
import org.urlshort.feign.data.UrlView;
import org.urlshort.service.UrlShortService;

@RestController
@RequiredArgsConstructor
public class UrlShortController {
    private final UrlShortService urlShortService;

    @PostMapping("/urls")
    public UrlView createUrl(@RequestBody UrlCreateRequest url){
        url.setUserid(Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName()));
        return urlShortService.createUrl(url);
    }

}
