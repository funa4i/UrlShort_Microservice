package org.urlshort.controllers.Feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.urlshort.controllers.Feign.data.RoleRequest;

@FeignClient(name = "acl-server")
public interface AccessControlApi {

    @PutMapping("/user/{id}/role")
    public void setUserRole(@PathVariable Long id,
                            @RequestBody RoleRequest role);
}
