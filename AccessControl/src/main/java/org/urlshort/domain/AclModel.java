package org.urlshort.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.urlshort.domain.entities.Path;
import org.urlshort.domain.entities.Role;
import org.urlshort.domain.entities.User;
import org.urlshort.domain.repositories.PathRepository;
import org.urlshort.domain.repositories.RoleRepository;
import org.urlshort.domain.repositories.UserRepository;
import org.urlshort.exceptions.NullObjectException;
import org.urlshort.exceptions.ObjectAlreadyExists;


@Component
@RequiredArgsConstructor
@Slf4j
public class AclModel {

    private final UserRepository userRep;

    private final PathRepository pathRep;

    private final RoleRepository roleRep;


    @Transactional
    public void setUserRole(Long id, String role){
        var rl = roleRep.findByName(role).orElseThrow(() -> new NullObjectException(Role.class, role));
        userRep.save(new User(id, rl));
    }

    @Transactional
    public void addRoleForPath(String path, String role){
        var newRole = roleRep.findByName(role).orElseThrow(() -> new NullObjectException(Role.class, role));
        var newPath = pathRep.findByName(path).orElse(new Path(path));
        pathRep.save(newPath);
        newRole.getPaths().add(newPath);
        roleRep.save(newRole);
    }

    @Transactional
    public void createPath(String path){
        if (!pathRep.existsByName(path)){
            pathRep.save(new Path(path));
            return;
        }
        throw new ObjectAlreadyExists(Path.class, path);
    }

    @Transactional
    public boolean accessCheck(String checkPath, Long userId){
        var user = userRep.findById(userId).orElseThrow(() ->
                new NullObjectException(User.class, userId.toString())
        );
        return user
                .getRole()
                .getPaths()
                .stream()
                .map(Path::getName)
                .anyMatch(x -> x.equals(checkPath)
                );
    }

    @Transactional
    public void createRole(String role){
        if (!roleRep.existsByName(role)){
            roleRep.save(new Role(role));
            return;
        }
        throw new ObjectAlreadyExists(Role.class, role);
    }
}
