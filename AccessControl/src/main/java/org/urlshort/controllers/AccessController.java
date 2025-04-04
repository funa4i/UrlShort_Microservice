package org.urlshort.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.urlshort.domain.data.AccessCheckAnswer;
import org.urlshort.domain.data.RoleRequest;
import org.urlshort.services.AccessControlService;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
public class AccessController {
    private final AccessControlService accessControlService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @PatchMapping("/users/{id}/roles")
    public void setUserRole(@PathVariable   @Min(1)     Long id,
                            @RequestBody    @NotBlank   RoleRequest role) throws JsonProcessingException {
        log.debug("Setting user role with {id: {} role: {}}", id, objectMapper.writeValueAsString(role));
        accessControlService.setUserRole(id, role.getRole());
    }

    @PostMapping("/users/{id}")
    public void createUser(@PathVariable @Min(1) Long id){
        log.debug("Creating user with {id: {}}", id);
        accessControlService.createUser(id);
    }

    @DeleteMapping("/users/{id}")
    public void revertUserCreate(@PathVariable @Min(1) Long id){
        log.debug("Revert create user by {id: {}}", id);
        accessControlService.deleteUser(id);
    }

    @GetMapping("/permissions/{userId}")
    public AccessCheckAnswer accessCheck(@RequestParam(name = "path")   @NotBlank   String path,
                                         @PathVariable                  @Min(1)     Long userId){
        log.debug("Getting permission by user id and path: {userId: {} path: {}}", userId, path);
        return new AccessCheckAnswer(accessControlService.accessCheck(path, userId));
    }

    @GetMapping("/admins")
    public List<Long> adminList(){
        log.debug("Get admin list");
        return accessControlService.adminListId();
    }
}
