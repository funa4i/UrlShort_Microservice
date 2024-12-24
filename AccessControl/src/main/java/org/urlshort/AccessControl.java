package org.urlshort;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AccessControl
{
    public static void main( String[] args )
    {
        SpringApplication.run(AccessControl.class, args);
    }
}
