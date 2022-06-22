package com.example.clientservice;

import com.example.clientservice.feign.BookServiceConnector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(clients = BookServiceConnector.class )
public class ClientService {

    public static void main(String[] args) {
        SpringApplication.run(ClientService.class);
    }

}
