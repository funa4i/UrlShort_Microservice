package org.urlshort.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.urlshort.domain.data.CreateLinkInfo;
import org.urlshort.domain.data.ExpiredLinkInfo;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationListener {
    private final EmailSender emailSender;

    @RabbitListener(queues = {"${spring.rabbitmq.queues.linkCreationNotification}"})
    public void createLinkInfoListener(CreateLinkInfo createLinkInfo){
        log.info(String.format("Received JSON createLinkInfo message -> %s", createLinkInfo.toString()));
        emailSender.sendCreateLinkInfoNotification(createLinkInfo);
    }


    @RabbitListener(queues = {"${spring.rabbitmq.queues.linkExpiredNotification}"})
    public void expiredLinkInfoListener(ExpiredLinkInfo expiredLinkInfo){
        log.info(String.format("Received JSON expired message -> %s", expiredLinkInfo.toString()));
        emailSender.sendExpiredLinkNotification(expiredLinkInfo);
    }

    @RabbitListener(queues = {"${spring.rabbitmq.queues.deadNotification}"})
    public void deadLetter(String message){
        log.warn(String.format("Dead letter -> ", message));
    }
}
