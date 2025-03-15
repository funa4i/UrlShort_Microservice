package org.urlshort.configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RabbitReceiveConfiguration{
    @Value("${spring.rabbitmq.host}")
    private String rabbitHost;
    @Value("${spring.rabbitmq.username}")
    private String rabbitUser;
    @Value("${spring.rabbitmq.password}")
    private String rabbitPassword;
    @Bean
    public ConnectionFactory connectionFactory(){
        var factory = new CachingConnectionFactory(rabbitHost);
        factory.setUsername(rabbitUser);
        factory.setPassword(rabbitPassword);
        return factory.getPublisherConnectionFactory();
    }
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory, List<Queue> queues,
                                   List<Exchange> exchanges, List<Binding> bindings){
        var admin = new RabbitAdmin(connectionFactory);
        queues.forEach(admin::declareQueue);
        exchanges.forEach(admin::declareExchange);
        bindings.forEach(admin::declareBinding);
        return admin;
    }

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory, MessageConverter jsonMessageConverter){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter);
        return rabbitTemplate;
    }
}

