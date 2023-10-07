package com.houlala.marketplace.upload;

import com.houlala.marketplace.configuration.FeignSupportConfig;
import com.houlala.marketplace.exception.MarketplaceException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(url = "https://upload.houlala.store", name = "upload", configuration = FeignSupportConfig.class)
public interface UploadFeign {
    @PostMapping(value = "/api/Upload/thumbnail", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadThumbnailImage(@RequestPart("image") MultipartFile image) throws MarketplaceException;

    @PostMapping(value = "/api/Upload/icons", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadIconImage(@RequestPart("image") MultipartFile image) throws MarketplaceException;

    @PostMapping(value = "/api/Upload/products", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadProductThumbnail(@RequestPart("image") MultipartFile image) throws MarketplaceException;
}
