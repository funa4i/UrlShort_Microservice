package org.urlshort.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.urlshort.domain.entity.User;
import org.urlshort.domain.repositories.UserRepository;
import org.urlshort.exceptions.NullObjectException;

@Component
@RequiredArgsConstructor
public class UserManager {
    private final UserRepository userRepository;

    public boolean existsById(Long userId){
        return userRepository.existsById(userId);
    }

    public boolean existsByEmail(String userEmail){
        return userRepository.existsByEmail(userEmail);
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }

    public void save(User user){
        userRepository.save(user);
    }

    public User findById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new NullObjectException(User.class, userId.toString()));
    }

    public User findUserByEmail(String userEmail){
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new NullObjectException(User.class, userEmail));
    }

    public Page<User> findAll(Integer page, Integer limit){
        return userRepository.findAll(PageRequest.of(page, limit));
    }
}
