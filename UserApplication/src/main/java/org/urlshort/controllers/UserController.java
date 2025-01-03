package org.urlshort.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.urlshort.domain.data.DecreaseResultAnswer;
import org.urlshort.domain.data.UserCreateRequest;
import org.urlshort.domain.data.UserInfo;
import org.urlshort.domain.data.UserMailRequest;
import org.urlshort.services.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userServ;

    @PutMapping("/user")
    public void createUser(@RequestBody UserCreateRequest user){
        userServ.createUser(user);
    }

    @PostMapping("/user/{id}/linksPerDay/decrement")
    public DecreaseResultAnswer decreaseUserLinks(@PathVariable Long id){
        return userServ.decreaseUserLinks(id);
    }

    @GetMapping("/user")
    public UserInfo userByEmail(@RequestBody UserMailRequest mailRequest){
        return userServ.userByEmail(mailRequest);
    }
    @GetMapping("/users")
    public Page<UserInfo> allUsers(@RequestParam Integer page,
                                   @RequestParam Integer limits)
    {
        return userServ.getUsers(page, limits);
    }

    @PostMapping("/user/{id}/linksPerDay")
    public void refactorUserLinks(@RequestParam(name = "count") Integer linksCount,
                                    @PathVariable Long id)
    {
        userServ.setUserLinks(id, linksCount);
    }

    @DeleteMapping("/user/{id}")
    public void revertCreateUser(@PathVariable Long id)
    {
        userServ.deleteUser(id);
    }


}
