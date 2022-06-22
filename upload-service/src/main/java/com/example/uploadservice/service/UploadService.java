package com.example.uploadservice.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {

    Resource uploadService(MultipartFile file) throws IOException;

    void init();

}
