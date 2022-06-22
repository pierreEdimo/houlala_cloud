package com.example.uploadservice.controller;

import com.example.uploadservice.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.apache.http.Header;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class Controller {


    private final UploadService service;


    @PostMapping("/upload")
    public String uploadFile(@RequestParam(name = "file")MultipartFile file){
        Resource resource;
        String url = "";
        try {
            resource =  this.service.uploadService(file);
            url = resource.getURI().toString();
        } catch (IOException e) {
            throw new RuntimeException("Could not upload file " + e.getMessage());
        }

        return url;
    }
}
