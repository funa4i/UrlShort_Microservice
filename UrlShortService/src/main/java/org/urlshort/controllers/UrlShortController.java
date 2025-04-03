package org.urlshort.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.urlshort.domain.data.UrlCreateRequest;
import org.urlshort.domain.data.UrlView;
import org.urlshort.domain.exceptions.NullObjectException;
import org.urlshort.services.UrlShortService;
import org.urlshort.domain.data.UserInfo;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UrlShortController implements ApplicationContextAware {
    private static ApplicationContext context;
    private static UserInfo getUserInfo(){
        return context.getBean(UserInfo.class);
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    private final UrlShortService urlShortService;
    private final ObjectMapper objectMapper ;

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
        var userInfo = getUserInfo();
        if (userInfo == null){
            throw new NullObjectException(UserInfo.class, "id");
        }
        urlCreateRequest.setUserid(userInfo.getUserId());
        log.info("/urls: {urlCreateRequest: {}}", objectMapper.writeValueAsString(urlCreateRequest));
        return urlShortService.createUrl(urlCreateRequest);
    }

    @GetMapping("/sh/{url}")
    public String getLong(@PathVariable("url") @NotBlank String shortUrl) {
        log.info("/{url}: {shortUrl: {}}", shortUrl);
        return urlShortService.getUrl(shortUrl);
    }



}
