package org.urlshort.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.urlshort.configuration.rabbit.RabbitQueues;
import org.urlshort.domain.data.CreateLinkInfo;
import org.urlshort.domain.data.ExpiredLinkInfo;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationListener {
    private final EmailSender emailSender;
    private final RabbitQueues rabbitQueues;

    @RabbitListener(queues = {"${spring.rabbitmq.queues.linkCreationQueue}"})
    public void createLinkInfoListener(CreateLinkInfo createLinkInfo){
        log.info("Received JSON createLinkInfo message {}", createLinkInfo.toString());
        emailSender.sendCreateLinkInfoNotification(createLinkInfo);
    }


    @RabbitListener(queues = {"${spring.rabbitmq.queues.linkExpiredQueue}"})
    public void expiredLinkInfoListener(ExpiredLinkInfo expiredLinkInfo){
        log.info("Received JSON expired message {}", expiredLinkInfo.toString());
        emailSender.sendExpiredLinkNotification(expiredLinkInfo);
    }

    @RabbitListener(queues = {"${spring.rabbitmq.queues.deadNotificationQueue}"})
    public void deadLetter(String message){
        log.warn("Dead letter -> {}", message);
    }
}
