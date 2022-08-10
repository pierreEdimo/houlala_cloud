package com.example.notificationclient.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationModel {
    private String subject;
    private String to;
    private String htmlBody;

    public NotificationModel(
            String subject,
            String to,
            String htmlBody
    ){
        this.subject = subject;
        this.to = to;
        this.htmlBody = htmlBody;
    }
}
