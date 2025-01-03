package org.urlshort.domain;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.urlshort.domain.entity.User;
import org.urlshort.domain.repositories.UserRepository;
import org.urlshort.exceptions.AlreadyExistsException;
import org.urlshort.exceptions.NullObjectException;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserModel {

    private final UserRepository userRep;

    @Value("${app.default.linksPerDay}")
    private Integer defaultLinksPerDay;

    @Transactional
    public void createUserWithId(Long userId, String email){
        if (userRep.existsById(userId)){
            throw new AlreadyExistsException(User.class, userId.toString());
        }
        if (userRep.existsByEmail(email)){
            throw new AlreadyExistsException(User.class, email);
        }
        var user = new User(userId, email, defaultLinksPerDay, defaultLinksPerDay, LocalDateTime.now());
        userRep.save(user);
    }

    @Transactional
    public void deleteUserById(Long id){
        userRep.deleteById(id);
    }

    @Transactional
    public boolean decreaseUserLinksLeft(Long userId){
        var user = userRep.findById(userId)
                .orElseThrow(() -> new NullObjectException(User.class, userId.toString()));
        if (LocalDateTime.now().isAfter(user.getDayCreate().plusDays(1))){
            user.setLinksLeft(user.getLinksPerDay());
            user.setDayCreate(LocalDateTime.now());
        }

        if (user.getLinksLeft() <= 0){
            return false;
        }
        user.setLinksLeft(user.getLinksLeft() - 1);
        return true;
    }

    @Transactional
    public User getUserByMail(String email){
        return userRep.findByEmail(email)
                .orElseThrow(() -> new NullObjectException(User.class, email));
    }

    @Transactional
    public Page<User> getAllUsers(Integer page, Integer limit){
        return userRep.findAll(PageRequest.of(page, limit));
    }

    @Transactional
    public void setUserLinksPerDay(Long userId, Integer count){
        userRep.findById(userId)
                .orElseThrow(()-> new RuntimeException("Dont find"))
                .setLinksPerDay(count);
    }
}
