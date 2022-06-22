package com.example.uploadservice.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class UploadServiceImpl implements UploadService {

    private final Path root = Paths.get("uploads");

    @Override
    public Resource uploadService(MultipartFile newFile) throws IOException {

        Resource result;

        Files.copy(newFile.getInputStream(), this.root.resolve(Objects.requireNonNull(newFile.getOriginalFilename())));
        Path file = this.root.resolve(newFile.getOriginalFilename());
        Resource resource = new UrlResource(file.toUri());
        if (resource.exists() || resource.isReadable()) {
            result = resource;
        } else {
            throw new RuntimeException("upload not possible");
        }

        return result;
    }

    @Override
    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize the proeject for upload");
        }
    }
}
