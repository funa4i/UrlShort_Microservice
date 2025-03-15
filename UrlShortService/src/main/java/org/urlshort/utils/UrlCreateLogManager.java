package org.urlshort.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.urlshort.domain.entities.UrlCreateLog;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UrlCreateLogManager {
    private final CriteriaBuilder criteriaBuilder;
    private final EntityManager entityManager;

    @Transactional
    public List<UrlCreateLog> getAllCreateByPeriod(LocalDateTime dateFrom, LocalDateTime dateTo){
        var query = criteriaBuilder.createQuery(UrlCreateLog.class);
        var root = query.from(UrlCreateLog.class);
        var predicateDate = criteriaBuilder.between(root.get("dateTime"), dateFrom, dateTo);
        query.select(root).where(predicateDate);
        var resultQuery =entityManager.createQuery(query);
        return resultQuery.getResultList();
    }
}
