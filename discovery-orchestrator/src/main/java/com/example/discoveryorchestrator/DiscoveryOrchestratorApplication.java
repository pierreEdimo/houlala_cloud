package com.example.discoveryorchestrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DiscoveryOrchestratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryOrchestratorApplication.class, args);
    }

}
