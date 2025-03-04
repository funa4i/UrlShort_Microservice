package org.urlshort.config;


import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("app")
public class PathsConfig {
    private List<String> publicApi;
}
