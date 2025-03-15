package org.urlshort.services;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.urlshort.domain.data.*;
import org.urlshort.domain.entity.User;
import org.urlshort.exceptions.AlreadyExistsException;
import org.urlshort.exceptions.NullObjectException;
import org.urlshort.mappers.UserMapper;
import org.urlshort.util.UserManager;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final UserManager userManager;
    @Value("${app.default.linksPerDay}")
    private Integer defaultLinksPerDay;

    @Transactional
    public void createUser( UserCreateRequest user){
        if (userManager.existsById(user.getId())){
            throw new AlreadyExistsException(User.class, user.getId().toString());
        }
        if (userManager.existsByEmail(user.getEmail())){
            throw new AlreadyExistsException(User.class, user.getEmail());
        }
        var newUser = userMapper.toUser(user);
        newUser.setLinksPerDay(defaultLinksPerDay);
        newUser.setLinksLeft(defaultLinksPerDay);
        userManager.save(newUser);
    }

    @Transactional
    public UserInfo userById(Long id){
        return userMapper.toUserInfo(userManager.findById(id));
    }

    @Transactional
    public boolean decreaseUserLinks(Long id){
        var user = userManager.findById(id);
        if (LocalDateTime.now().isAfter(user.getDayCreate().plusDays(1))){
            user.setLinksLeft(user.getLinksPerDay());
            user.setDayCreate(LocalDateTime.now());
        }

        if (user.getLinksLeft() <= 0){
            userManager.save(user);
            return false;
        }
        user.setLinksLeft(user.getLinksLeft() - 1);
        userManager.save(user);
        return true;
    }

    @Transactional
    public UserInfo userByEmail( UserMailRequest mail){
        return userMapper.toUserInfo(
                userManager.findUserByEmail(mail.getEmail())
        );
    }

    @Transactional
    public void deleteUser(Long id){
        if(userManager.existsById(id)){
            throw new NullObjectException(User.class, id.toString());
        }
        userManager.delete(id);
    }

    @Transactional
    public Page<UserInfo> getUsers(Integer page, Integer limits){
        return userManager.findAll(page, limits)
                .map(userMapper::toUserInfo);
    }

    @Transactional
    public void setUserLinks(Long userId, Integer linksCount){
        var user = userManager.findById(userId);
        user.setLinksPerDay(linksCount);
        userManager.save(user);
    }
}
