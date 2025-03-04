package org.urlshort.feign.clients;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.urlshort.feign.data.AccessCheckAnswer;


@FeignClient(name = "accessControl-service")
public interface AccessControlApi {
    @GetMapping("/permissions/{userId}")
    AccessCheckAnswer accessCheck(@RequestParam(name = "path") String path, Long UserId);
}

