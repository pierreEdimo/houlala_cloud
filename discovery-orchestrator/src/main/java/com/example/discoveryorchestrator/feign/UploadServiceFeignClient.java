package com.example.discoveryorchestrator.feign;

import com.example.discoveryorchestrator.config.FeignSupportConfig;
import com.example.discoveryorchestrator.exception.OrchestratorException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "upload", url = "https://upload.houlala.store", configuration = FeignSupportConfig.class)
public interface UploadServiceFeignClient {
    @PostMapping(value = "/api/Upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadImage(@RequestPart(value = "image") MultipartFile image) throws OrchestratorException;
}
