package org.urlshort.controllers;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.urlshort.domain.data.AccessCheckAnswer;
import org.urlshort.domain.data.RoleRequest;
import org.urlshort.services.AccessControlService;


@RestController
@RequiredArgsConstructor
public class AccessController {
    private final AccessControlService accessControlService;

    @PatchMapping("/users/{id}/roles")
    public void setUserRole(@PathVariable   @Min(1)     Long id,
                            @RequestBody    @NotBlank   RoleRequest role)
    {
        accessControlService.setUserRole(id, role.getRole());
    }

    @PostMapping("/users/{id}")
    public void createUser(@PathVariable @Min(1) Long id){
        accessControlService.createUser(id);
    }

    @DeleteMapping("/users/{id}")
    public void revertUserCreate(@PathVariable @Min(1) Long id){
        accessControlService.deleteUser(id);
    }

    @GetMapping("/permissions/{userId}")
    public AccessCheckAnswer accessCheck(@RequestParam(name = "path")   @NotBlank   String path,
                                         @PathVariable                  @Min(1)     Long UserId){
        return new AccessCheckAnswer(accessControlService.accessCheck(path, UserId));
    }
}
