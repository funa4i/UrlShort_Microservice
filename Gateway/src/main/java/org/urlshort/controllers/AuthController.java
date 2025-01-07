package org.urlshort.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.urlshort.advice.enums.MethodType;
import org.urlshort.domain.annotations.Auth;
import org.urlshort.feign.data.CreateUserRequest;
import org.urlshort.feign.data.JwtResponse;
import org.urlshort.feign.data.UserValid;
import org.urlshort.service.AuthService;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sing-in")
    public JwtResponse singIn(@RequestBody CreateUserRequest newUser){
        return authService.singIn(newUser);
    }

    @PostMapping("/log-in")
    public JwtResponse logIn(@RequestBody UserValid user){
        return authService.logIn(user);
    }

}
