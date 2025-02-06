package org.urlshort.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.urlshort.domain.entities.User;
import org.urlshort.domain.repositories.UserRepository;

@Component
@RequiredArgsConstructor
public class UserManager {
    private final UserRepository userRepository;

    public boolean existsById(Long userId){
        return userRepository.existsById(userId);
    }

    public void save(User user){
        userRepository.save(user);
    }
}
