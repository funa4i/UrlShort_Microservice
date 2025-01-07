package org.urlshort.domain.aspects;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.urlshort.domain.annotations.Auth;
import org.urlshort.exceptions.InvalidRoleForePath;
import org.urlshort.service.AclService;

@Aspect
@Component
@RequiredArgsConstructor
public class AuthAspect {

    private final AclService aclService;

    @Pointcut("@annotation(annot)")
    public void validateAccess(Auth annot){}

    @Before("validateAccess(annot)")
    public void beforeValidateAccess(Auth annot){
        var name = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!aclService.accessCheck(annot.methodType().toString() + annot.path(), Long.parseLong(name)).getAccess()){
            throw new InvalidRoleForePath("");
        }
    }

}
