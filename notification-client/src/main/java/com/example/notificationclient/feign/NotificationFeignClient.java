package com.example.notificationclient.feign;

import com.example.notificationclient.model.NotificationModel;
import com.example.notificationclient.exception.NotificationException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "https://houlala-notification.herokuapp.com/api/", name = "notifications")
public interface NotificationFeignClient {
    @PostMapping("/Email")
    String sendEmailNotification(@RequestBody NotificationModel model) throws NotificationException;
}
