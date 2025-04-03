package org.urlshort.domain.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.urlshort.domain.entities.Url;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Long>, JpaSpecificationExecutor<Url> {
     Optional<Url> findByShortUrl(String shortUrl);

     Boolean existsByShortUrl(String shortUrl);

     List<Url> findByValidUntilBefore(LocalDateTime dateTime);
}
