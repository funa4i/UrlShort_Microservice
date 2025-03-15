package org.urlshort.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.urlshort.domain.data.DecreaseResultAnswer;
import org.urlshort.domain.data.UserCreateRequest;
import org.urlshort.domain.data.UserInfo;
import org.urlshort.domain.data.UserMailRequest;
import org.urlshort.services.UserService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/users")
    public void createUser(@RequestBody @Valid UserCreateRequest userCreateRequest) throws JsonProcessingException {
        log.info("POST /users: {userCreateRequest: {}}", objectMapper.writeValueAsString(userCreateRequest));
        userService.createUser(userCreateRequest);
    }

    @PatchMapping("/user/{id}/linksPerDay/decrement")
    public DecreaseResultAnswer decreaseUserLinks(@PathVariable @Min(1) Long id){
        log.info("PATCH /user/{id}/linksPerDay/decrement: {id: {}}", id);
        return new DecreaseResultAnswer(
                userService.decreaseUserLinks(id)
        );
    }
    @GetMapping("/users/{id}")
    public UserInfo userById(@PathVariable Long id) {
        log.info("GET /users/{id}: {id: {}}", id);
        return userService.userById(id);
    }

    @GetMapping("/user")
    public UserInfo userByEmail(@RequestBody @Valid UserMailRequest userMailRequest) throws JsonProcessingException {
        log.info("GET /user: {userMailRequest: {}}", objectMapper.writeValueAsString(userMailRequest));
        return userService.userByEmail(userMailRequest);
    }
    @GetMapping("/users")
    public Page<UserInfo> allUsers(@RequestParam @NotNull @Min(0) Integer page,
                                   @RequestParam @NotNull @Min(1) Integer limits) {
        log.info("GET /users: {page: {} limits: {}}", page, limits);
        return userService.getUsers(page, limits);
    }

    @PatchMapping("/users/{userId}/linksPerDay")
    public void refactorUserLinks(@RequestParam(name = "count") Integer linksCount,
                                    @PathVariable Long userId) {
        log.info("PATCH /users/{userId}/linksPerDay: {linscCount: {}, userId: {}}", linksCount, userId);
        userService.setUserLinks(userId, linksCount);
    }

    @DeleteMapping("/users/{id}")
    public void revertCreateUser(@PathVariable @Min(1) @NotNull Long id) {
        log.info("DELETE /users/{id}: {id: {}}", id);
        userService.deleteUser(id);
    }
}
