package org.urlshort.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.urlshort.advice.enums.MethodType;
import org.urlshort.domain.annotations.Auth;
import org.urlshort.feign.data.RoleRequest;
import org.urlshort.service.AclService;

@RestController
@RequiredArgsConstructor
public class AclController {

    private final AclService aclServ;


    @PutMapping("/user/{id}/role")
    @Auth(methodType = MethodType.PUT, path = "/user/{id}/role",
            requiredRole = {"admin"})
    public void setUserRole(@PathVariable Long id,
                            @RequestBody RoleRequest role) {
        aclServ.setUserRole(id, role.getRole());
    }

    @PutMapping("/permissions/{role}")
    @Auth(methodType = MethodType.PUT, path = "/permissions/{role}",
            requiredRole = {"admin"})
    public void createNewRole(@PathVariable String role){
        aclServ.createNewRole(role);
    }
}
