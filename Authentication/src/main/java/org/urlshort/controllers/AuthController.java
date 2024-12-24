package org.urlshort.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("")
    public String hnhnh(){
        return "HelloWorld";
    }


}
