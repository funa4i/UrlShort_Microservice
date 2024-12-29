package org.urlshort.services;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.urlshort.domain.data.CreateUserRequest;
import org.urlshort.domain.data.UserValid;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtServ jwtServ;

    public String logIn(){
        return null;
    }

    public String singIn(@Valid CreateUserRequest newUser){

    }



}
