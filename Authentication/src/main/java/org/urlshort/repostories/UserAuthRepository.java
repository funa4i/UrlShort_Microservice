package org.urlshort.repostories;

import org.urlshort.entities.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthRepository extends JpaRepository<UserAuth, Long>
{

}
