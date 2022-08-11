package com.example.notificationclient.service;

import com.example.notificationclient.exception.NotificationException;
import com.example.notificationclient.feign.NotificationFeignClient;
import com.example.notificationclient.feign.OrderFeignClient;
import com.example.notificationclient.model.NotificationModel;
import com.example.notificationclient.model.Order;
import com.example.notificationclient.model.dto.SendEmaildto;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final Configuration configuration;
    private final NotificationFeignClient feignClient;

    private final OrderFeignClient orderFeignClient;

    @Override
    public void sendEmail(SendEmaildto newEmailDto) {
        try {
            Template template = configuration.getTemplate("order.template.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, newEmailDto);

            NotificationModel model = new NotificationModel(
                    newEmailDto.getSubject(),
                    newEmailDto.getTo(),
                    html
            );

            String response = this.feignClient.sendEmailNotification(model);
            System.out.println(response);


        } catch (IOException | TemplateException | NotificationException e) {
            throw new RuntimeException(e);
        }
    }
}
