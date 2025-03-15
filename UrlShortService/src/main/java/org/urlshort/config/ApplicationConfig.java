package org.urlshort.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public CriteriaBuilder criteriaBuilder(EntityManager entityManager){
        return entityManager.getCriteriaBuilder();
    }

}
