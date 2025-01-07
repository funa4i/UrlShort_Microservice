package org.urlshort.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.urlshort.advice.enums.MethodType;
import org.urlshort.domain.annotations.Auth;
import org.urlshort.feign.data.UserInfo;
import org.urlshort.feign.data.UserMailRequest;
import org.urlshort.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userServ;


    @GetMapping("/user")
    @Auth(methodType = MethodType.GET, path = "/user",
            requiredRole = {"admin"})
    public UserInfo userByEmail(@RequestBody UserMailRequest mailRequest){
        return userServ.userByEmail(mailRequest);
    }

    @GetMapping("/users")
    @Auth(methodType = MethodType.GET, path = "/users",
            requiredRole = {"admin"})
    public Page<UserInfo> allUsers(@RequestParam Integer page,
                                   @RequestParam Integer limits)
    {
        return userServ.getUsers(page, limits);
    }

    @PostMapping("/user/{id}/linksPerDay")
    @Auth(methodType = MethodType.POST, path = "/user/{id}/linksPerDay",
            requiredRole = {"admin"})
    public void refactorUserLinks(@RequestParam(name = "count") Integer linksCount,
                                    @PathVariable Long id)
    {
        userServ.setUserLinks(id, linksCount);
    }
}
