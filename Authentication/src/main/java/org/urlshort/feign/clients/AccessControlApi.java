package org.urlshort.feign.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "accessControl-service")
public interface AccessControlApi {

    @PostMapping("/users/{id}")
    void createUser(@PathVariable Long id);

    @DeleteMapping("/users/{id}")
    void revertCreateUser(@PathVariable Long id);
}
