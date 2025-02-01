package org.urlshort.feign.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.urlshort.feign.data.UserCreateRequest;

@FeignClient(name = "user-service")
public interface UserApplicationApi {

    @PutMapping("/user")
    void createUser(@RequestBody UserCreateRequest user);

    @DeleteMapping("/user/{id}")
    void revertCreateUser(@PathVariable Long id);
}
