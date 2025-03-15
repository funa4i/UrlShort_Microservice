package org.urlshort.feign.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.urlshort.domain.data.DecreaseResultAnswer;
import org.urlshort.feign.data.UserInfo;

@FeignClient(name = "user-service")
public interface UserApplicationApi {
    @PatchMapping("/users/{id}/linksPerDay/decrement")
    DecreaseResultAnswer decreaseUserLinks(@PathVariable Long id);

    @GetMapping("/users/{id}")
    UserInfo userById(@PathVariable Long id);
}
