package org.urlshort.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.urlshort.domain.entities.Path;
import org.urlshort.domain.entities.User;
import org.urlshort.enums.Roles;
import org.urlshort.exceptions.AlreadyExistsException;
import org.urlshort.utils.RoleManager;
import org.urlshort.utils.UserManager;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccessControlService {
   private final UserManager userManager;
   private final RoleManager roleManager;

   @Value("${role.default}")
   private String defaultRole;

   @Transactional
   public void setUserRole(Long id, String roleName){
        var role = roleManager.findByName(roleName);
        var user = userManager.findById(id);
        user.setRole(role);
        userManager.save(user);
   }

   @Transactional
   public List<Long> adminListId(){
       var role = roleManager.findByName(Roles.Admin.name());
       return userManager.findAllUsersByRole(role).stream().map(User::getId).collect(Collectors.toList());
   }

   @Transactional
   public void createUser(Long id){
       if (userManager.existsById(id)){
           throw new AlreadyExistsException(User.class, id.toString());
       }
       var user = new User();
       user.setId(id);
       userManager.save(user);
       setUserRole(id, defaultRole);
   }

   @Transactional
   public boolean accessCheck(String path,  Long userId){
       var user = userManager.findById(userId);
       return user.getRole().getPaths().stream()
               .map(Path::getName)
               .anyMatch(x -> x.equals(path));
   }

   @Transactional
   public void deleteUser(Long id){
       var user = userManager.findById(id);
       userManager.deleteUser(user);
   }
}
