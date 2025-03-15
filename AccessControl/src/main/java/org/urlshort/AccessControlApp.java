package org.urlshort;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AccessControlApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(AccessControlApp.class, args);
    }
}
