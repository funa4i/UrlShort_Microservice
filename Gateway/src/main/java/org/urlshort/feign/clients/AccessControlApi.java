package org.urlshort.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.urlshort.feign.data.AccessCheckAnswer;
import org.urlshort.feign.data.RoleRequest;

@FeignClient(name = "acl-server")
public interface AccessControlApi {

    @PutMapping("/user/{id}/role")
    void setUserRole(@PathVariable Long id, @RequestBody RoleRequest role);

    @PutMapping("/permissions/{role}")
    void createNewRole(@PathVariable String role);

    @GetMapping("/permission/{id}")
    AccessCheckAnswer accessCheck(@RequestParam(name = "path") String path, @PathVariable Long id);

    @PutMapping("/path")
    void addPath(@RequestParam(name="path") String path);

    @PostMapping("/permissions/{role}")
    void addRoleForPath(@RequestParam(name = "path") String path,
                               @PathVariable String role);
}
