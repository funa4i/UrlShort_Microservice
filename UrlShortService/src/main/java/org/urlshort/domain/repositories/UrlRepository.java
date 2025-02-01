package org.urlshort.domain.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.urlshort.domain.entities.Url;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Long> {
     Optional<Url> findByShortUrl(String shortUrl);

     Boolean existsByShortUrl(String shortUrl);
}
