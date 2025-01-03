package org.urlshort.configuration;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.urlshort.feign.RetrieveMessageErrorDecoder;

@Configuration
public class FeignClientsConfiguration {


    @Bean
    public ErrorDecoder errorDecoder(){
        return new RetrieveMessageErrorDecoder();
    }

}
