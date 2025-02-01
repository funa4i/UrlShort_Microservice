package org.urlshort.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.urlshort.domain.entities.User;

@Component
@RequiredArgsConstructor
public class UserManager {
    private final UserManager userManager;

    public boolean existsById(Long userId){
        return userManager.existsById(userId);
    }

    public void save(User user){
        userManager.save(user);
    }
}
