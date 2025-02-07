package org.urlshort.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.urlshort.feign.data.UrlCreateRequest;
import org.urlshort.feign.data.UrlView;
import org.urlshort.service.UrlShortService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UrlShortController {
    private final UrlShortService urlShortService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/urls")
    public UrlView createUrl(@RequestBody UrlCreateRequest urlCreateRequest) throws JsonProcessingException {
        urlCreateRequest.setUserid(
                Long.parseLong(
                        SecurityContextHolder.getContext().getAuthentication().getName()
                ));
        log.info("urls: {urlCreateRequest: {}}", objectMapper.writeValueAsString(objectMapper));
        return urlShortService.createUrl(urlCreateRequest);
    }

}
