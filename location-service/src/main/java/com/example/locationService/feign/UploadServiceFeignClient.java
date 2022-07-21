package com.example.locationService.feign;

import com.example.locationService.config.FeignSupportConfig;
import com.example.locationService.exception.LocationServiceException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "upload", url = "upload", configuration = FeignSupportConfig.class)
public interface UploadServiceFeignClient {
    @PostMapping(value = "/newFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadFile(@RequestPart(name = "file") MultipartFile file) throws LocationServiceException;
}
