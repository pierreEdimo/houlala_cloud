package com.example.notificationclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class NotificationClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationClientApplication.class, args);
    }

}
