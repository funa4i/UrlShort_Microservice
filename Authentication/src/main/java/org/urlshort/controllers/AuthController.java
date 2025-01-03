package org.urlshort.controllers;


import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.urlshort.domain.data.CreateUserRequest;
import org.urlshort.domain.data.JwtResponse;
import org.urlshort.domain.data.UserValid;
import org.urlshort.services.AuthService;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sing-in")
    public JwtResponse singIn(@RequestBody CreateUserRequest newUser){
        return new JwtResponse(authService.singIn(newUser));
    }

    @PostMapping("/log-in")
    public JwtResponse logIn(@RequestBody UserValid user){
        return new JwtResponse(authService.logIn(user));
    }

}
