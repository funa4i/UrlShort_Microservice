package org.urlshort.services;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.urlshort.feign.clients.AccessControlApi;
import org.urlshort.utils.UrlCreateLogManager;

import java.time.LocalDateTime;

@Service
@EnableScheduling
@RequiredArgsConstructor
public class NotificationSchedule {
    private final RabbitSender rabbitSender;
    private final UrlCreateLogManager urlCreateLogManager;
    private final AccessControlApi accessControlApi;

    @Scheduled(cron = "50 23 * * * *")
    public void scheduleTaskUsingCronExpression() {
        var timeNow = LocalDateTime.now();
        var dateFrom = LocalDateTime.of(timeNow.getYear(), timeNow.getMonth(), timeNow.getDayOfMonth(), 0, 0);
        var dateTo = dateFrom.plusDays(1);
        var adminIdList = accessControlApi.adminList();
        var createCount = (long) urlCreateLogManager.getAllCreateByPeriod(dateFrom, dateTo).size();
        adminIdList.forEach((x) -> rabbitSender.sendCreateInfoNotificationAsync(x, createCount, dateFrom));
    }
}
