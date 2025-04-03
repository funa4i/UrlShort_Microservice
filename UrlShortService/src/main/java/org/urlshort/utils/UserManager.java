package org.urlshort.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.urlshort.domain.entities.Url;
import org.urlshort.domain.entities.User;
import org.urlshort.domain.exceptions.NullObjectException;
import org.urlshort.domain.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserManager {
    private final UserRepository userRepository;

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NullObjectException(User.class, userId.toString()));
    }

    public boolean existsById(Long userId) {
        return userRepository.existsById(userId);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
