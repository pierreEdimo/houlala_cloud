package com.example.notificationclient.service;

import com.example.notificationclient.model.dto.SendEmaildto;

public interface EmailService {
    void sendEmail(SendEmaildto newEmailDto);
}
