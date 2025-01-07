package org.urlshort.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.urlshort.feign.data.CreateUserRequest;
import org.urlshort.feign.data.JwtResponse;
import org.urlshort.feign.data.UserValid;

@FeignClient("auth-server")
public interface AuthenticationApi {
    @PostMapping("/sing-in")
    JwtResponse singIn(@RequestBody CreateUserRequest newUser);

    @PostMapping("/log-in")
    JwtResponse logIn(@RequestBody UserValid user);

}
