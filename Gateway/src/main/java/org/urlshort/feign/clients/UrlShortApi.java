package org.urlshort.feign.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.urlshort.feign.data.UrlCreateRequest;
import org.urlshort.feign.data.UrlView;

@FeignClient(name = "url-service")
public interface UrlShortApi {

    @PostMapping("/urls")
    UrlView createUrl(@RequestBody UrlCreateRequest url);

}
