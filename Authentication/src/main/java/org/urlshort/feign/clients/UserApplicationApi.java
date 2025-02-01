package org.urlshort.feign.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.urlshort.feign.data.UserCreateRequest;

@FeignClient(name = "user-service")
public interface UserApplicationApi {

    @PostMapping("/users")
    void createUser(@RequestBody UserCreateRequest user);

    @DeleteMapping("/users/{id}")
    void revertCreateUser(@PathVariable Long id);
}
