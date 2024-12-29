package org.urlshort.domain;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.urlshort.controllers.Feign.AccessControlApi;
import org.urlshort.controllers.Feign.data.RoleRequest;
import org.urlshort.domain.data.CreateUserRequest;
import org.urlshort.domain.repostories.UserAuthRepository;
import org.urlshort.mappers.UserMapper;

@Component
@RequiredArgsConstructor
public class AuthModel {

    private final UserAuthRepository userRep;

    private final AccessControlApi accessControl;

    private final UserMapper userMapper;

    @Transactional
    public void createNewUser(CreateUserRequest user, String role){
        var newUser = userMapper.toUser(user);
        userRep.save(newUser);
        try {
            accessControl.setUserRole(newUser.getId(), new RoleRequest(role));
            //TODO: После реализации UserService допилить создание
        }
        catch (FeignException ex){
            userRep.deleteById(newUser.getId());
            throw ex;
        }
    }


}
