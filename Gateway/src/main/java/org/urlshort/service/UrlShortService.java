package org.urlshort.service;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.urlshort.feign.data.UrlCreateRequest;
import org.urlshort.feign.data.UrlView;
import org.urlshort.feign.clients.UrlShortApi;

@Service
@RequiredArgsConstructor
public class UrlShortService {

    private final UrlShortApi urlShortApi;

    @Value("${app.UrlPath")
    private String urlPath;

    public String getUrl(@NotBlank String url){
        return urlPath + urlShortApi.getLong(url);
    }

    public void deleteUrl(@Min(1) @NotNull Long id){
        urlShortApi.deleteUrl(id);
    }

    public UrlView createUrl(@Valid UrlCreateRequest url){
        var view = urlShortApi.createUrl(url);
        view.setShortUrl(urlPath + view.getShortUrl());
        return view;
    }

    public Page<UrlView> getUrls(
            @Min(0) @NotNull Integer page,
            @Min(1) @NotNull Integer limit){
        return urlShortApi.getUrls(page, limit);
    }

}


