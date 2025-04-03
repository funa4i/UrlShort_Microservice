package org.urlshort.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.urlshort.domain.data.CreateLinkInfo;
import org.urlshort.domain.data.ExpiredLinkInfo;
import org.urlshort.domain.entities.Url;
import org.urlshort.domain.entities.User;
import org.urlshort.domain.exceptions.UncheckedException;
import org.urlshort.feign.clients.AccessControlApi;
import org.urlshort.feign.clients.UserApplicationApi;
import org.urlshort.feign.data.UserInfo;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitSender {
    private final RabbitTemplate rabbitTemplate;
    private final UserApplicationApi userApplicationApi;
    private final ObjectMapper objectMapper;
    @Value("${spring.rabbitmq.exchangers.notificationExchange}")
    private String notificationExchange;
    @Value("${spring.rabbitmq.routs.expiredLinkRout}")
    private String expiredLinkRoutKey;
    @Value("${spring.rabbitmq.routs.createInfoRoutKey}")
    private String creationInfoRoutKey;

    @Async
    public void sendCreateInfoNotificationAsync(Long userId, Long createAmount, LocalDate date){
        try {
            UserInfo  user = userApplicationApi.userById(userId);
            CreateLinkInfo createLinkInfo = new CreateLinkInfo(user.getEmail(), createAmount, date);
            String stringMessage = objectMapper.writeValueAsString(createLinkInfo);
            Message message = new Message(stringMessage.getBytes());
            rabbitTemplate.send(notificationExchange, creationInfoRoutKey, message);
        }
        catch (UncheckedException | FeignException ex){
            log.info("Error on getting data from UserApplication");
        }
        catch (JsonProcessingException ex){
            log.warn("Json map exception");
        }
    }

    @Async
    public void sendExpiredLinkNotificationAsync(Long userId, Url url){
        try {
            UserInfo user = userApplicationApi.userById(userId);
            ExpiredLinkInfo linkInfo = new ExpiredLinkInfo(user.getEmail(), url.getFullUrl(), url.getShortUrl());
            String stringMessage = objectMapper.writeValueAsString(linkInfo);
            Message message = new Message(stringMessage.getBytes());
            rabbitTemplate.send(notificationExchange, expiredLinkRoutKey, message);
        }
        catch (UncheckedException | FeignException ex){
            log.info("Error on getting data from UserApplication");
        }
        catch (JsonProcessingException ex){
            log.warn("Json map exception");
        }

    }
}
