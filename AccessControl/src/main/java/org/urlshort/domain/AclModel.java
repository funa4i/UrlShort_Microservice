package org.urlshort.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.urlshort.domain.entities.Path;
import org.urlshort.domain.entities.Role;
import org.urlshort.domain.entities.User;
import org.urlshort.exceptions.InvalidRoleException;
import org.urlshort.domain.repositories.PathRepository;
import org.urlshort.domain.repositories.RoleRepository;
import org.urlshort.domain.repositories.UserRepository;
import org.urlshort.exceptions.NullObjectException;


@Component
@RequiredArgsConstructor
@Slf4j
public class AclModel {

    private final UserRepository userRep;

    private final PathRepository pathRep;

    private final RoleRepository roleRep;


    @Transactional
    public void setUserRole(Long id, String role){
        var newRole = roleRep.findByName(role).orElseThrow(() -> new InvalidRoleException(role));
        userRep.save(new User(id, newRole));
    }

    @Transactional
    public void addRoleForPath(String path, String role){
        var newRole = roleRep.findByName(role).orElseThrow(() -> new InvalidRoleException(role));
        var newPath = pathRep.findByName(path).orElse(new Path(path));
        pathRep.save(newPath);
        newRole.getPaths().add(newPath);
        roleRep.save(newRole);
    }

    @Transactional
    public boolean accessCheck(String checkPath, Long userId){
        var user = userRep.findById(userId).orElseThrow(() ->
                new NullObjectException(User.class.getSimpleName(), userId.toString())
        );
        var role = roleRep.findRoleWithPaths(user.getRole().getId());
        var path = pathRep.findByName("some").get();
        System.out.println(user.getRole().getPaths().size() + "РАЗМЕР");
        return user
                .getRole()
                .getPaths()
                .stream()
                .map(Path::getName)
                .anyMatch(x -> x.equals(checkPath)
                );
    }

    @Transactional
    public void addRole(String role){
        if (roleRep.findByName(role).isEmpty()){
            roleRep.save(new Role(role));
        }
    }
}
