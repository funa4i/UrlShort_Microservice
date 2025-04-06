package org.example.config;

import org.example.advice.GeneralExceptionHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AdviceProperties.class)
public class AdviceConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public GeneralExceptionHandler generalExceptionHandler(){
        return new GeneralExceptionHandler();
    }
}
