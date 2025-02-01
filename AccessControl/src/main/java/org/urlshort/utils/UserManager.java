package org.urlshort.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.urlshort.domain.entities.User;
import org.urlshort.domain.repositories.UserRepository;
import org.urlshort.exceptions.NullObjectException;


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

    public User findById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new NullObjectException(User.class, userId.toString()));
    }

    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }
}
