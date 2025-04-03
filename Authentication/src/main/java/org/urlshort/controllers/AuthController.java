package org.urlshort.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.urlshort.domain.data.CreateUserRequest;
import org.urlshort.domain.data.JwtResponse;
import org.urlshort.domain.data.UserValid;
import org.urlshort.services.AuthService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final ObjectMapper objectMapper;

    @PostMapping("/sing-in")
    public JwtResponse singIn(@RequestBody @Valid CreateUserRequest createUserRequest) throws JsonProcessingException {
        log.debug("POST Sing-in: {createUserRequest: {}}", objectMapper.writeValueAsString(createUserRequest));
        return new JwtResponse(authService.singIn(createUserRequest));
    }

    @PostMapping("/log-in")
    public JwtResponse logIn(@RequestBody @Valid UserValid userValid) throws JsonProcessingException {
        log.debug("POST Log-in: {userValid: {}}", objectMapper.writeValueAsString(userValid));
        return new JwtResponse(authService.logIn(userValid));
    }

}
