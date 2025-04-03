package org.urlshort.services;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.urlshort.config.DelayValues;
import org.urlshort.feign.clients.AccessControlApi;
import org.urlshort.utils.UrlManager;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@EnableScheduling
@RequiredArgsConstructor
public class NotificationSchedule {
    private final RabbitSender rabbitSender;
    private final UrlManager urlManager;
    private final AccessControlApi accessControlApi;
    private final DelayValues delayValues;

    @Scheduled(cron = "#{@delayValues.getCronDelay()}")
    public void scheduleTaskUsingCronExpression() {
        var dateFrom =LocalDate.now();
        var dateTo = dateFrom.plusDays(1);
        var adminIdList = accessControlApi.adminList();
        var createCount = (long) urlManager.findAllCreateAtByPeriod(dateFrom, dateTo).size();
        adminIdList.forEach((x) -> rabbitSender.sendCreateInfoNotificationAsync(x, createCount, dateFrom));
    }

    @Scheduled(fixedRateString = "#{@delayValues.getLinkCheckDelay().toMillis()}")
    public void scheduleTaskUsingFixedDelay(){
        var timeTo = LocalDateTime.now().minusNanos(delayValues.getLinkDuration().toNanos());
        var resultList = urlManager.findAllExpiredLinks();
        resultList.stream().parallel().forEach(urlManager::delete);
    }
}
