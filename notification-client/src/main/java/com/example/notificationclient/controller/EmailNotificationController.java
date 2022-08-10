package com.example.notificationclient.controller;

import com.example.notificationclient.model.dto.SendEmaildto;
import com.example.notificationclient.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/emails")
public class EmailNotificationController {

    private final EmailService service;

    @PostMapping("")
    public void sendEmailNotification(@RequestBody SendEmaildto newEmailDto){
        this.service.sendEmail(newEmailDto);
    }
}
