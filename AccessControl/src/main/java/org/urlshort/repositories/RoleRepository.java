package org.urlshort.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.urlshort.domain.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
