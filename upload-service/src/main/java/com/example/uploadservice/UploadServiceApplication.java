package com.example.uploadservice;

import com.example.uploadservice.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
@RequiredArgsConstructor
public class UploadServiceApplication implements CommandLineRunner {

    @Resource
    private final UploadService service;

    public static void main(String[] args) {
        SpringApplication.run(UploadServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        this.service.init();
    }
}
