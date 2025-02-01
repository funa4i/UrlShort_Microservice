package org.urlshort.feign.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.urlshort.feign.data.RoleRequest;

@FeignClient(name = "accessControl-service")
public interface AccessControlApi {

    @PutMapping("/user/{id}")
    void createUser(@PathVariable Long id);

    @DeleteMapping("/user/{id}")
    void revertCreateUser(@PathVariable Long id);
}
