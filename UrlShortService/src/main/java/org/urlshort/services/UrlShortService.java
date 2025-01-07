package org.urlshort.services;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.urlshort.domain.UrlShortModel;
import org.urlshort.domain.data.UrlCreateRequest;
import org.urlshort.domain.data.UrlView;
import org.urlshort.mappers.UrlMapper;

@Service
@RequiredArgsConstructor
public class UrlShortService {

    private final UrlShortModel urlShortModel;

    private final UrlMapper urlMapper;

    public String getUrl(@NotBlank String url){
        return urlShortModel.getUrlByShort(url).getFullUrl();
    }

    public void deleteUrl(@Min(1) @NotNull Long id){
        urlShortModel.deleteUrl(id);
    }

    public UrlView createUrl(@Valid UrlCreateRequest url){

        var newUrl = urlShortModel.createUrl(url, url.getUserid());
        newUrl.setShortUrl(newUrl.getShortUrl());
        return urlMapper.toUrlView(newUrl);
    }

    public Page<UrlView> getUrls(
            @Min(0) @NotNull Integer page,
            @Min(1) @NotNull Integer limit){

        return  urlShortModel.getAllUrls(page, limit)
                .map(urlMapper::toUrlView);

    }

}


