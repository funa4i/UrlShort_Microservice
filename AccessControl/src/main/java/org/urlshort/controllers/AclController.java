package org.urlshort.controllers;


import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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
                            @RequestBody Map<String, String> role)
    {
        aclServ.setUserRole(id, role.get("role"));
    }

    @PostMapping("/permissions/{role}")
    public void addRoleForPath(@RequestParam(name = "path") String path,
                                @PathVariable String role){
        aclServ.addRoleForPath(path, role);

    }

    @GetMapping("/permission/{id}")
    public Map<String, Boolean> accessCheck(@RequestParam(name = "path") String path,
                               @PathVariable Long id){
        var ret = new HashMap<String, Boolean>();
        ret.put("access", aclServ.accessCheck(path, id));
        return ret;
    }

    @PutMapping("/permissions/{role}")
    public void createNewRole(@PathVariable String role){
        aclServ.createNewRole(role);
    }
}
