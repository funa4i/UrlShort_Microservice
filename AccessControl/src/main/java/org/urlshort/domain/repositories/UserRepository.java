package org.urlshort.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.urlshort.domain.entities.Role;
import org.urlshort.domain.entities.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByRole(Role role);
}
