package org.urlshort;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.bcrypt.BCrypt;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class Authentication
{
    public static void main( String[] args )
    {
        SpringApplication.run(Authentication.class, args);

    }
}
