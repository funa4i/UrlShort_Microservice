package org.urlshort.services;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.urlshort.domain.AclModel;

@Service
@RequiredArgsConstructor
public class AclService {

   private final AclModel aclModel;

   public void setUserRole(@Min(1) Long id, @NotBlank String role){
        aclModel.setUserRole(id, role);
   }

   public void addRoleForPath(@NotBlank String path, @NotBlank String role){
       aclModel.addRoleForPath(path, role);
   }

   public boolean accessCheck(@NotBlank String path, @Min(1) Long userId){
       return aclModel.accessCheck(path, userId);
   }

   public  void createNewRole(@NotBlank String role){
       aclModel.createRole(role);
   }

   public void createPath(@NotBlank String path){
       aclModel.createPath(path);
   }

   public void deleteUser(@Min(1) @NotBlank Long id){
       aclModel.deleteUser(id);
   }

   public void createUser(@Min(1) @NotNull Long id){
       aclModel.createUser(id);
   }
}
