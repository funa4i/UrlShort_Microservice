package org.urlshort.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.urlshort.configuration.rabbit.RabbitPaths;

@Configuration
@RequiredArgsConstructor
public class RabbitRouteConfiguration {
    private final RabbitConfigVariables rabbitConfigVariables;
    @Bean
    public Queue linkCreationQ(){
        return QueueBuilder.durable(rabbitConfigVariables.getQueues().getLinkCreationQueue())
                .deadLetterExchange(rabbitConfigVariables.getExchanges().getNotificationExchange())
                .deadLetterRoutingKey(rabbitConfigVariables.getQueues().getDeadNotificationQueue())
                .build();
    }
    @Bean Queue expiredLinkQ(){
        return QueueBuilder.durable(rabbitConfigVariables.getQueues().getLinkExpiredQueue())
                .deadLetterExchange(rabbitConfigVariables.getExchanges().getNotificationExchange())
                .deadLetterRoutingKey(rabbitConfigVariables.getQueues().getDeadNotificationQueue())
                .build();
    }
    @Bean
    public Queue deadNotificationQ(){
        return new Queue(rabbitConfigVariables.getQueues().getDeadNotificationQueue(), true);
    }
    @Bean
    public DirectExchange notificationExchange(){
        return new DirectExchange(rabbitConfigVariables.getExchanges().getNotificationExchange());
    }

    @Bean
    public Binding notificationBind(Queue linkCreationQ,
                                    DirectExchange notificationExchange){
        return BindingBuilder.bind(linkCreationQ).to(notificationExchange)
                .with(rabbitConfigVariables.getPaths().getNotification() + "." + linkCreationQ.getName());
    }

    @Bean
    public Binding deadBind(Queue deadNotificationQ,
                            DirectExchange notificationExchange){
        return BindingBuilder.bind(deadNotificationQ).to(notificationExchange)
                .with(rabbitConfigVariables.getPaths().getNotification() + "." + deadNotificationQ.getName());
    }
    @Bean
    public Binding expiredBind(Queue expiredLinkQ,
                            DirectExchange  notificationExchange){
        return BindingBuilder.bind(expiredLinkQ).to(notificationExchange)
                .with(rabbitConfigVariables.getPaths().getNotification() + "." + expiredLinkQ.getName());
    }
}
