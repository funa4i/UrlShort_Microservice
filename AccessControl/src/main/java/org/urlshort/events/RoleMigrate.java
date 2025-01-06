package org.urlshort.events;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.urlshort.domain.entities.Role;
import org.urlshort.domain.repositories.RoleRepository;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class RoleMigrate implements ApplicationListener<ApplicationReadyEvent> {

    private final RoleRepository roleRepository;

    @Value("${role.defaultRole}")
    private String defaultRole;

    @Value("#{'${role.roles}'.split(' ')}")
    private ArrayList<String> roles;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (!roleRepository.existsByName(defaultRole)){
            roleRepository.save(new Role(defaultRole));
        }
        roles.forEach(x -> {
            if (!roleRepository.existsByName(x)){
                roleRepository.save(new Role(x));
            }
        });
    }
}
