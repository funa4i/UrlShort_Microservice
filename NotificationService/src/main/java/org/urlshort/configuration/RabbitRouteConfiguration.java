package org.urlshort.configuration;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.Name;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitRouteConfiguration {
    @Value("${spring.rabbitmq.queues.linkCreationNotification}")
    private String linkCreationQueue;
    @Value("${spring.rabbitmq.queues.linkExpiredNotification}")
    private String linkExpiredQueue;
    @Value("${spring.rabbitmq.queues.deadNotification}")
    private String deadNotificationQueue;
    @Value("${spring.rabbitmq.exchanges.notificationExchange}")
    private String notificationExchange;
    @Value("${spring.rabbitmq.paths.notification}")
    private String notificationRout;
    @Bean
    public Queue linkCreationQ(){
        return QueueBuilder.durable(linkCreationQueue)
                .deadLetterExchange(notificationExchange)
                .deadLetterRoutingKey(deadNotificationQueue)
                .build();
    }
    @Bean Queue expiredLinkQ(){
        return QueueBuilder.durable(linkExpiredQueue)
                .deadLetterExchange(notificationExchange)
                .deadLetterRoutingKey(deadNotificationQueue)
                .build();
    }
    @Bean
    public Queue deadNotificationQ(){
        return new Queue(deadNotificationQueue, true);
    }
    @Bean
    public DirectExchange notificationExchange(){
        return new DirectExchange(notificationExchange);
    }

    @Bean
    public Binding notificationBind(Queue linkCreationQ,
                                    DirectExchange notificationExchange){
        return BindingBuilder.bind(linkCreationQ).to(notificationExchange)
                .with(notificationRout + "." + linkCreationQ.getName());
    }

    @Bean
    public Binding deadBind(Queue deadNotificationQ,
                            DirectExchange notificationExchange){
        return BindingBuilder.bind(deadNotificationQ).to(notificationExchange)
                .with(notificationRout + "." + deadNotificationQ.getName());
    }
    @Bean
    public Binding expiredBind(Queue expiredLinkQ,
                            DirectExchange  notificationExchange){
        return BindingBuilder.bind(expiredLinkQ).to(notificationExchange)
                .with(notificationRout + "." + expiredLinkQ.getName());
    }
}
