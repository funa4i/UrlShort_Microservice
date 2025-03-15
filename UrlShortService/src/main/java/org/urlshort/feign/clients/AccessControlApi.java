package org.urlshort.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "accessControl-service")
public interface AccessControlApi {
    @GetMapping("/admins")
    List<Long> adminList();
}
