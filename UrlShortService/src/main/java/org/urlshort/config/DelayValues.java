package org.urlshort.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Data
@ConfigurationProperties(prefix = "app.delays")
@Configuration
public class DelayValues {
     private Duration linkDuration;
     private String cronDelay;
     private Duration linkCheckDelay;
}
