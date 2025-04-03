package org.urlshort.configuration;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import org.urlshort.configuration.rabbit.RabbitExchanges;
import org.urlshort.configuration.rabbit.RabbitPaths;
import org.urlshort.configuration.rabbit.RabbitQueues;

@Configuration
@Getter(AccessLevel.PUBLIC)
@AllArgsConstructor
public class RabbitConfigVariables {
    private RabbitQueues queues;
    private RabbitPaths paths;
    private RabbitExchanges exchanges;
}
