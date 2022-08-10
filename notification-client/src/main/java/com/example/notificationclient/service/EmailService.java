package com.example.notificationclient.service;

import com.example.notificationclient.model.dto.SendEmaildto;

import java.util.Map;

public interface EmailService {
    void sendEmail(SendEmaildto newEmailDto);
}
