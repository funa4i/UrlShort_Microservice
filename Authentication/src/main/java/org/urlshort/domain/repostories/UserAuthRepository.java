package org.urlshort.domain.repostories;

import org.urlshort.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthRepository extends JpaRepository<User, Long>
{

}
