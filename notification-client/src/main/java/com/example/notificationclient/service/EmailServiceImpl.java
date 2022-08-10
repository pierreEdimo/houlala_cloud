package com.example.notificationclient.service;

import com.example.notificationclient.exception.NotificationException;
import com.example.notificationclient.feign.NotificationFeignClient;
import com.example.notificationclient.model.NotificationModel;
import com.example.notificationclient.model.dto.SendEmaildto;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;


@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final Configuration configuration;

    private final NotificationFeignClient feignClient;

    @Override
    public void sendEmail(SendEmaildto newEmailDto) {
        try {
            Template template = configuration.getTemplate("test.template.ftl");
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
