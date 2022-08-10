package com.example.uploadclient.controller;

import com.example.uploadclient.exception.UploadException;
import com.example.uploadclient.feign.UploadFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class uploadController {

    private final UploadFeign feign;

    @PostMapping("/upload")
    public String uploadImage(@RequestPart MultipartFile image) {
        String url;
        try {
            url = this.feign.uploadImage(image);
        } catch (UploadException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
        return url;
    }
}
