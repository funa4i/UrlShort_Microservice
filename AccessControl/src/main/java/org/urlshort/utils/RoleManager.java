package org.urlshort.utils;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.urlshort.domain.entities.Role;
import org.urlshort.domain.repositories.RoleRepository;
import org.urlshort.exceptions.NullObjectException;

@Component
@RequiredArgsConstructor
public class RoleManager {
    private final RoleRepository roleRepository;

    public Role findByName(String roleName){
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new NullObjectException(Role.class, roleName));
    }

    public void save(Role role){
        roleRepository.save(role);
    }

}
