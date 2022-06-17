package com.example.marketplaceclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MarketplaceClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarketplaceClientApplication.class, args);
    }

}
