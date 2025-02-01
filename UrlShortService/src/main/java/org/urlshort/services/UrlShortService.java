package org.urlshort.services;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.urlshort.domain.data.UrlCreateRequest;
import org.urlshort.domain.data.UrlView;
import org.urlshort.domain.entities.User;
import org.urlshort.domain.exceptions.AttemptCountException;
import org.urlshort.domain.exceptions.NullObjectException;
import org.urlshort.feign.clients.UserApplicationApi;
import org.urlshort.mappers.UrlMapper;
import org.urlshort.utils.ShortUrUtil;
import org.urlshort.utils.UrlManager;
import org.urlshort.utils.UserManager;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UrlShortService {
    private final UrlMapper urlMapper;
    private final UserManager userManager;
    private final UrlManager urlManager;
    private final UserApplicationApi userApplicationApi;
    @Value(value = "${app.default.linkDurationDays}")
    private Long linkDurationDays;

    @Transactional
    public String getUrl(@NotBlank String shortUrl){
        var url = urlManager.findByShortUrl(shortUrl);
        url.setIterations(url.getIterations() - 1);
        if(url.getIterations() == 0){
            urlManager.delete(url);
        }
        return url.getFullUrl();
    }

    @Transactional
    public void deleteUrl(Long id){
        if (!userManager.existsById(id)){
            throw new NullObjectException(User.class, id.toString());
        }
        urlManager.delete(id);
    }

    @Transactional
    public UrlView createUrl(@Valid UrlCreateRequest urlCreateRequest){
        var url = urlMapper.toUrl(urlCreateRequest);

        var user = new User(urlCreateRequest.getUserid());
        if (!userManager.existsById(urlCreateRequest.getUserid())) {
            userManager.save(user);
        }

        Boolean answer = userApplicationApi
                .decreaseUserLinks(urlCreateRequest.getUserid())
                .getReduced();

        if (!answer){
            throw new AttemptCountException();
        }

        url.setUser(user);
        var shorturl = "";
        do {
            shorturl = ShortUrUtil.getNextValue();

        }while (urlManager.existsByShortUrl(shorturl));

        url.setShortUrl(shorturl);
        url.setValidUntil(LocalDateTime.now().plusDays(linkDurationDays));
        urlManager.save(url);
        return urlMapper.toUrlView(url);
    }

    public Page<UrlView> getUrls(Integer page, Integer limit){
        return  urlManager.findAll(page, limit)
                .map(urlMapper::toUrlView);
    }
}


