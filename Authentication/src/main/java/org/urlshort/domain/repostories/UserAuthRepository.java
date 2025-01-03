package org.urlshort.domain.repostories;

import org.urlshort.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAuthRepository extends JpaRepository<User, Long>
{
        Optional<User> findByLogin(String login);

        Boolean existsByLogin(String login);
}
