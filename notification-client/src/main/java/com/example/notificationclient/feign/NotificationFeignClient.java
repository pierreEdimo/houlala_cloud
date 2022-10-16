package com.example.notificationclient.feign;

import com.example.notificationclient.model.NotificationModel;
import com.example.notificationclient.exception.NotificationException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "http://notification-service:8004/api/", name = "notifications")
public interface NotificationFeignClient {
    @PostMapping("/Email")
    String sendEmailNotification(@RequestBody NotificationModel model) throws NotificationException;
}
