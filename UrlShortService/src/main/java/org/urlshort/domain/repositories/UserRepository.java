package org.urlshort.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.urlshort.domain.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
