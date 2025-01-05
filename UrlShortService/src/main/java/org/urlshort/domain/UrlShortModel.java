package org.urlshort.domain;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.urlshort.domain.entities.Url;
import org.urlshort.domain.entities.User;
import org.urlshort.domain.exceptions.AttemptCountException;
import org.urlshort.domain.exceptions.NotFoundException;
import org.urlshort.domain.exceptions.NullObjectException;
import org.urlshort.domain.exceptions.UncheckedException;
import org.urlshort.domain.repositories.UrlRepository;
import org.urlshort.domain.repositories.UserRepository;
import org.urlshort.feign.clients.UserApplicationApi;
import org.urlshort.utils.ShortUrUtil;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UrlShortModel {

    private final UrlRepository urlRep;

    private final UserRepository userRep;

    private final UserApplicationApi userApplicationApi;

    @Value(value = "${app.default.linkDurationDays}")
    private Long linkDurationDays;


    @Transactional
    public Url getUrlByShort(String shortUrl){
        return urlRep
                .findByShortUrl(shortUrl)
                .orElseThrow(
                        () -> new NullObjectException(Url.class, shortUrl)
                );
    }


    @Transactional
    public void deleteUrl(Long id){
        urlRep.deleteById(id);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Url createUrl(Url url, Long userId){
        var user = new User(userId);
        if (!userRep.existsById(userId)) {
            userRep.save(user);
        }

        Boolean answer = userApplicationApi.decreaseUserLinks(url.getUserId()).getReduced();

        if (!answer){
            throw new AttemptCountException();
        }

        url.setUser(user);
        var shorturl = "";
        do {
            shorturl = ShortUrUtil.getNextValue();

        }while (urlRep.existsBuShortUrl(shorturl));

        url.setShortUrl(shorturl);
        url.setValidUntil(LocalDateTime.now().plusDays(linkDurationDays));
        urlRep.save(url);
        return url;
    }

    @Transactional
    public Page<Url> getAllUrls(Integer offset, Integer limit){
        return urlRep.findAll(PageRequest.of(offset, limit));
    }

}
