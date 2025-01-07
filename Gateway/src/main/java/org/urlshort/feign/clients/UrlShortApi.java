package org.urlshort.feign.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.urlshort.feign.data.UrlCreateRequest;
import org.urlshort.feign.data.UrlView;

@FeignClient(name = "url-serv")
public interface UrlShortApi {

    @GetMapping("/urls")
    Page<UrlView> getUrls(@RequestParam Integer page, @RequestParam Integer limit);

    @DeleteMapping("/url/{id}")
    void deleteUrl(@PathVariable Long id);

    @PostMapping("/url")
    UrlView createUrl(@RequestBody UrlCreateRequest url);

    @GetMapping("/{url}")
    String getLong(@PathVariable("url") String shortUrl);
}
