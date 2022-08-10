package com.example.categoryservice.feign;

import com.example.categoryservice.config.FeignSupportConfig;
import com.example.categoryservice.exception.CategoryException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(url = "https://houlala-storage.herokuapp.com/", name = "upload", configuration = FeignSupportConfig.class)
public interface UploadFeignService {
    @PostMapping(value = "/api/Upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadImage(@RequestPart MultipartFile image) throws CategoryException;
}
