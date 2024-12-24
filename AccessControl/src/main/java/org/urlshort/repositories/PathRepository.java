package org.urlshort.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.urlshort.domain.entities.Path;

public interface PathRepository extends JpaRepository<Path, Long> {
}
