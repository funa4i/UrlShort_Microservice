package org.urlshort.events;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.urlshort.controllers.AclController;
import org.urlshort.controllers.AuthController;
import org.urlshort.controllers.UrlShortController;
import org.urlshort.controllers.UserController;
import org.urlshort.domain.annotations.Auth;
import org.urlshort.service.AclService;
import org.reflections.Reflections;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class PathValidation implements ApplicationListener<ApplicationReadyEvent> {

    private final AclService aclService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("Validating Paths");

        Reflections reflections = new Reflections("org.urlshort");
        Set<Class<?>> st = reflections.getTypesAnnotatedWith(RestController.class);

//         beans = applicationContext.getBeansWithAnnotation(RestController.class);

        var beansNames = new HashMap<String, List<Auth>>();

        // Выборка всех методов с аннотацией Auth и складывание в словарик
        st.forEach((x) -> {
                    var lst = Arrays
                            .stream(x.getDeclaredMethods())
                            .filter(
                                    (method) -> method.isAnnotationPresent(Auth.class))
                            .map(meth -> meth.getDeclaredAnnotation(Auth.class))
                            .toList();
                    beansNames.put(x.getSimpleName(), lst);
                }
        );
        try {
            beansNames.forEach((x, y) -> {

                y.forEach(annotation -> {
                    String path = annotation.methodType().toString() + annotation.path();
                    aclService.createPath(path);
                    Arrays.stream(annotation.requiredRole()).forEach(
                            (role) -> {
                                aclService.addRoleForPath(path, role);
                            }
                    );
                });
            });
        }
        catch (Exception exception){
            log.error(exception.getMessage());
            System.exit(-1);
        }
    }
}
