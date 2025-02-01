package org.urlshort.services;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.urlshort.domain.data.CreateUserRequest;
import org.urlshort.domain.data.UserValid;
import org.urlshort.domain.entities.User;
import org.urlshort.exceptions.*;
import org.urlshort.feign.clients.AccessControlApi;
import org.urlshort.feign.clients.UserApplicationApi;
import org.urlshort.feign.data.UserCreateRequest;
import org.urlshort.mappers.UserMapper;
import org.urlshort.util.HashUtil;
import org.urlshort.util.UserAuthManager;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final AccessControlApi accessControl;
    private final UserApplicationApi userApplication;
    private final UserMapper userMapper;
    private final UserAuthManager userAuthManager;
    private final JwtServ jwtServ;

    @Transactional
    public String logIn(@Valid UserValid userValid){
        var user = userAuthManager.findByLogin(userValid.getLogin());
        if (!HashUtil.validatePassword(
                userValid.getPassword(), user.getPassword())){
            throw new InvalidAuthorizeException();
        }
        return jwtServ.generateToken(user);
    }

    @Transactional
    public String singIn(@Valid CreateUserRequest newUser){
        var user = userMapper.toUser(newUser);
        if (userAuthManager.existsByLogin(newUser.getLogin())){
            throw new AlreadyExistsException(User.class, newUser.getLogin());
        }
        user.setRegisterDate(LocalDateTime.now());
        userAuthManager.save(user);
        try {
            accessControl.createUser(user.getId());
            userApplication.createUser(
                    new UserCreateRequest(user.getId(), newUser.getEmail())
            );
        }
        catch (NotFoundException |
               NullObjectException |
               UncheckedException ex){
            userApplication.revertCreateUser(user.getId());
            accessControl.revertCreateUser(user.getId());
            userAuthManager.deleteById(user.getId());
            throw ex;
        }
        return jwtServ.generateToken(user);
    }
}
