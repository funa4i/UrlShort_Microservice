package org.urlshort.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.urlshort.domain.entities.Path;

import java.util.Optional;

public interface PathRepository extends JpaRepository<Path, Long> {

    Optional<Path> findByName(String name);

    Boolean existsByName(String name);
}
