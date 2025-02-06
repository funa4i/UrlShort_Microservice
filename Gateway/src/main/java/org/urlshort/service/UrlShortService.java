package org.urlshort.service;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.urlshort.feign.data.UrlCreateRequest;
import org.urlshort.feign.data.UrlView;
import org.urlshort.feign.clients.UrlShortApi;

@Service
@RequiredArgsConstructor
public class UrlShortService {
    private final UrlShortApi urlShortApi;

    @Value("${app.UrlPath}")
    private String urlPath;

    public UrlView createUrl(@Valid UrlCreateRequest url){
        var view = urlShortApi.createUrl(url);
        view.setShortUrl(urlPath + view.getShortUrl());
        return view;
    }


}


