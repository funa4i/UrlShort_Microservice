package org.urlshort.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.urlshort.domain.data.CreateUserRequest;

@RestController
public class AuthController {


    @PostMapping("sing-in")
    public ResponseEntity<?> singIn(@RequestBody CreateUserRequest newUser){
        return null;
    }




    @PostMapping("/log-in")
    public void logIn(){

    }


}
