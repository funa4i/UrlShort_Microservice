package org.urlshort.domain.annotations;


import org.urlshort.advice.ExceptionView;
import org.urlshort.advice.enums.ExceptionAnswer;
import org.urlshort.advice.enums.MethodType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {

    MethodType methodType();

    String path();

    String[] requiredRole();
}
