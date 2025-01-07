package org.urlshort.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.urlshort.domain.entities.User;
import org.urlshort.exceptions.*;
import org.urlshort.feign.clients.AccessControlApi;
import org.urlshort.feign.clients.UserApplicationApi;
import org.urlshort.feign.data.RoleRequest;
import org.urlshort.domain.data.CreateUserRequest;
import org.urlshort.domain.repostories.UserAuthRepository;
import org.urlshort.feign.data.UserCreateRequest;
import org.urlshort.mappers.UserMapper;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthModel {

    private final UserAuthRepository userRep;

    private final AccessControlApi accessControl;

    private final UserApplicationApi userApplication;

    private final UserMapper userMapper;

    @Transactional
    public User createNewUser(CreateUserRequest user){
        var newUser = userMapper.toUser(user);
        if (userRep.existsByLogin(newUser.getLogin())){
            throw new AlreadyExistsException(User.class, newUser.getLogin());
        }
        newUser.setRegisterDate(LocalDateTime.now());
        userRep.save(newUser);
        try {
            accessControl.createUser(newUser.getId());
            userApplication.createUser(
                    new UserCreateRequest(newUser.getId(), user.getEmail())
            );
        }
        catch (NotFoundException |
                NullObjectException |
                UncheckedException ex){
            userApplication.revertCreateUser(newUser.getId());
            accessControl.revertCreateUser(newUser.getId());
            userRep.deleteById(newUser.getId());
            throw ex;
        }
        return newUser;
    }

    @Transactional
    public Optional<User> getUser(String login){
        return userRep.findByLogin(login);
    }
}
