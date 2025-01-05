package org.urlshort.feign.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.urlshort.domain.data.DecreaseResultAnswer;
import org.urlshort.feign.data.UserCreateRequest;

@FeignClient(name = "user-server")
public interface UserApplicationApi {

    @PostMapping("/user/{id}/linksPerDay/decrement")
    public DecreaseResultAnswer decreaseUserLinks(@PathVariable Long id);

}
