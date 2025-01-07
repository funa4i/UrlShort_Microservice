package org.urlshort.service;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.urlshort.feign.data.AccessCheckAnswer;
import org.urlshort.feign.data.RoleRequest;
import org.urlshort.feign.clients.AccessControlApi;

@Service
@RequiredArgsConstructor
public class AclService {

    private final AccessControlApi aclApi;

   public void setUserRole(@Min(1) Long id, @NotBlank String role){
        aclApi.setUserRole(id, new RoleRequest(role));
   }

   public AccessCheckAnswer accessCheck(@NotBlank String path, @Min(1) Long userId){
       return aclApi.accessCheck(path, userId);
   }

   public  void createNewRole(@NotBlank String role){
       aclApi.createNewRole(role);
   }

   public void createPath(@NotBlank String path){
       aclApi.addPath(path);
   }

    public void addRoleForPath(@NotBlank String path, @NotBlank String role){
        aclApi.addRoleForPath(path, role);
    }


}
