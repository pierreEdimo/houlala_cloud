package com.example.notificationclient.model.dto;

import com.example.notificationclient.model.Order;
import com.example.notificationclient.model.UserInformation;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SendEmaildto {
    private UserInformation userInformation;
    private String subject;
    private String to;
    private Order order;
}
