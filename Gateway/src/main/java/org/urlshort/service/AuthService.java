package org.urlshort.service;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.urlshort.feign.data.CreateUserRequest;
import org.urlshort.feign.data.JwtResponse;
import org.urlshort.feign.data.UserValid;
import org.urlshort.feign.clients.AuthenticationApi;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationApi authApi;

    public JwtResponse logIn(@Valid UserValid userValid){
        return  authApi.logIn(userValid);
    }

    public JwtResponse singIn(@Valid CreateUserRequest newUser){
        return authApi.singIn(newUser);
    }




}
