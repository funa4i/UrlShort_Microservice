package org.urlshort.feign.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.urlshort.feign.data.RoleRequest;

@FeignClient(name = "acl-server")
public interface AccessControlApi {

    @PutMapping("/user/{id}/role")
    void setUserRole(@PathVariable Long id, @RequestBody RoleRequest role);

    @DeleteMapping("/user/{id}")
    public void revertCreateUser(@PathVariable Long id);
}
