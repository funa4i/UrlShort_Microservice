package org.urlshort.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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

    private final UserService userService;

    @PostMapping("/users")
    public void createUser(@RequestBody @Valid UserCreateRequest user){
        userService.createUser(user);
    }

    @PatchMapping("/user/{id}/linksPerDay/decrement")
    public DecreaseResultAnswer decreaseUserLinks(@PathVariable @Min(1) Long id){
        return new DecreaseResultAnswer(
                userService.decreaseUserLinks(id)
        );
    }

    @GetMapping("/user")
    public UserInfo userByEmail(@RequestBody @Valid UserMailRequest mailRequest){
        return userService.userByEmail(mailRequest);
    }
    @GetMapping("/users")
    public Page<UserInfo> allUsers(@RequestParam @NotNull @Min(0) Integer page,
                                   @RequestParam @NotNull @Min(1) Integer limits)
    {
        return userService.getUsers(page, limits);
    }

    @PatchMapping("/users/{userId}/linksPerDay")
    public void refactorUserLinks(@RequestParam(name = "count") Integer linksCount,
                                    @PathVariable Long userId)
    {
        userService.setUserLinks(userId, linksCount);
    }

    @DeleteMapping("/users/{id}")
    public void revertCreateUser(@PathVariable @Min(1) @NotNull Long id)
    {
        userService.deleteUser(id);
    }


}
