package org.urlshort.domain.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.urlshort.domain.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

}
