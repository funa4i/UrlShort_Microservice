package org.urlshort.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.urlshort.advice.enums.MethodType;
import org.urlshort.domain.annotations.Auth;
import org.urlshort.feign.data.UrlCreateRequest;
import org.urlshort.feign.data.UrlView;
import org.urlshort.service.UrlShortService;

@RestController
@RequiredArgsConstructor
public class UrlShortController {

    private final UrlShortService urlShortService;

    @GetMapping("/urls")
    @Auth(methodType = MethodType.GET, path = "/urls",
            requiredRole = {"admin"})
    public Page<UrlView> getUrls(@RequestParam Integer page,
                                 @RequestParam Integer limit){
        return urlShortService.getUrls(page, limit);
    }

    @DeleteMapping("/url/{id}")
    @Auth(methodType = MethodType.DELETE, path = "/url/{id}",
            requiredRole = {"admin"})
    public void deleteUrl(@PathVariable Long id) {
        urlShortService.deleteUrl(id);
    }

    @PostMapping("/url")
    @Auth(methodType = MethodType.POST, path = "/url",
            requiredRole = {"user", "admin"})
    public UrlView createUrl(@RequestBody UrlCreateRequest url){
        url.setUserid(Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName()));
        return urlShortService.createUrl(url);
    }

    @GetMapping("/{url}")
    public ResponseEntity<String> getLong(@PathVariable("url") String shortUrl) throws JsonProcessingException {
        var url = urlShortService.getUrl(shortUrl);
        return ResponseEntity.status(303).header("Location", url).body("");

    }
}
