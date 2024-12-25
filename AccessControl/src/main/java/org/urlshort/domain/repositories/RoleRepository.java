package org.urlshort.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.urlshort.domain.entities.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);

    @Query("SELECT r FROM Role r JOIN FETCH r.paths WHERE r.id = :roleId")
    Role findRoleWithPaths(@Param("roleId") Long roleId);



}
