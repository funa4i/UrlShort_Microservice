package org.urlshort.feign.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.urlshort.feign.data.UserInfo;
import org.urlshort.feign.data.UserMailRequest;

@FeignClient(name = "user-serv")
public interface UserApplicationApi {

    @GetMapping("/user")
    UserInfo userByEmail(@RequestBody UserMailRequest mailRequest);

    @GetMapping("/users")
    Page<UserInfo> allUsers(@RequestParam Integer page,
                                   @RequestParam Integer limits);

    @PostMapping("/user/{id}/linksPerDay")
    void refactorUserLinks(@RequestParam(name = "count") Integer linksCount, @PathVariable Long id);

}



