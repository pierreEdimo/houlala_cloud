package com.example.uploadclient.feign;

import com.example.uploadclient.config.FeignSupportConfig;
import com.example.uploadclient.exception.UploadException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;


@FeignClient(url = "https://houlala-storage.herokuapp.com/", name = "upload", configuration = FeignSupportConfig.class)
public interface UploadFeign {

    @PostMapping(value = "/api/Upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadImage(@RequestPart MultipartFile image) throws UploadException;

}
