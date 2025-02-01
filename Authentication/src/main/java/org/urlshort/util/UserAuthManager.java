package org.urlshort.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.urlshort.domain.entities.User;
import org.urlshort.domain.repostories.UserAuthRepository;
import org.urlshort.exceptions.NullObjectException;

@Component
@RequiredArgsConstructor
public class UserAuthManager {
    private final UserAuthRepository userAuthRepository;

    public boolean existsByLogin(String userLogin){
        return userAuthRepository.existsByLogin(userLogin);
    }

    public User findByLogin(String userLogin){
        return userAuthRepository.findByLogin(userLogin).orElseThrow(() -> new NullObjectException(User.class, userLogin));
    }

    public void save(User user){
        userAuthRepository.save(user);
    }

    public void deleteById(Long userId){
        userAuthRepository.deleteById(userId);
    }
}
