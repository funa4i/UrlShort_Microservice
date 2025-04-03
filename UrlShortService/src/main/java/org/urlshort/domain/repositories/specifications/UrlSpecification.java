package org.urlshort.domain.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.urlshort.domain.entities.Url;

import java.time.LocalDate;

public class UrlSpecification {

    public static Specification<Url> dateRange(LocalDate dateFrom, LocalDate dateTo){
        if (dateFrom == null && dateTo == null){
            return null;
        }
        if (dateFrom == null){
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.greaterThan(root.get("createdAt"), dateFrom);
        }
        if (dateTo == null){
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.lessThan(root.get("createdAt"), dateTo);
        }

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("createdAt"), dateFrom, dateTo);
    }
}
