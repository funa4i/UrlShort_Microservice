package org.urlshort.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.urlshort.domain.UserDetail;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username){
        return new UserDetail(Long.parseLong(username));
    }
}
