package org.example.config;

import lombok.extern.slf4j.Slf4j;
import org.example.advice.GeneralExceptionHandler;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Configuration
@Slf4j
@AutoConfigureAfter({WebMvcAutoConfiguration.class, WebClientAutoConfiguration.class })
public class AdviceConfiguration {

    @Bean
    public GeneralExceptionHandler generalExceptionHandler(){
        log.info(">>>General exception handler was initialised<<<");
        return new GeneralExceptionHandler();
    }
}
