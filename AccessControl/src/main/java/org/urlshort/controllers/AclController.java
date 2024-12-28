package org.urlshort.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.urlshort.domain.data.AccessCheckAnswer;
import org.urlshort.domain.data.RoleRequest;
import org.urlshort.services.AclService;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequiredArgsConstructor
public class AclController {

    private final AclService aclServ;

    @GetMapping
    public String v(){
        return "HelloWorld";
    }

    @PutMapping("/user/{id}/role")
    public void setUserRole(@PathVariable Long id,
                            @RequestBody RoleRequest role)
    {
        aclServ.setUserRole(id, role.getRole());
    }

    @PostMapping("/permissions/{role}")
    public void addRoleForPath(@RequestParam(name = "path") String path,
                                @PathVariable String role){
        aclServ.addRoleForPath(path, role);
    }

    @PutMapping("/path")
    public void addPath(@RequestParam(name="path") String path){
        aclServ.createPath(path);
    }

    @GetMapping("/permission/{id}")
    public AccessCheckAnswer accessCheck(@RequestParam(name = "path") String path,
                                            @PathVariable Long id){

        return new AccessCheckAnswer(aclServ.accessCheck(path, id));
    }

    @PutMapping("/permissions/{role}")
    public void createNewRole(@PathVariable String role){
        aclServ.createNewRole(role);
    }
}
