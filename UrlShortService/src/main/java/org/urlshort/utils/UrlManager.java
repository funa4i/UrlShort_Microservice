package org.urlshort.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.urlshort.domain.entities.Url;
import org.urlshort.domain.exceptions.NullObjectException;
import org.urlshort.domain.repositories.UrlRepository;

import java.awt.print.Pageable;

@Component
@RequiredArgsConstructor
public class UrlManager {
    private final UrlRepository urlRepository;

    public Url findByShortUrl(String shortUrl){
        return urlRepository.findByShortUrl(shortUrl)
                .orElseThrow(
                        () -> new NullObjectException(Url.class, shortUrl)
                );
    }

    public void delete(Url url){
        urlRepository.delete(url);
    }

    public void delete(Long urlId){
        urlRepository.deleteById(urlId);
    }

    public boolean existsByShortUrl(String shortUrl){
        return urlRepository.existsByShortUrl(shortUrl);
    }

    public void save(Url url) {
        urlRepository.save(url);
    }

    public Page<Url> findAll(Integer page, Integer limit){
        return urlRepository.findAll(PageRequest.of(page, limit));
    }
}
