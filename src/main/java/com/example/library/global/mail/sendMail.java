package com.example.library.global.mail;

import com.example.library.send.service.SendMailServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class sendMail {

    private static JavaMailSender emailSender;
    private static SendMailServiceImpl sendMailService;

    public sendMail(JavaMailSender emailSender, SendMailServiceImpl sendMailService) {
        sendMail.emailSender = emailSender;
        sendMail.sendMailService = sendMailService;
    }

    public static void send(String event, String email, String name) {
        SimpleMailMessage message = new SimpleMailMessage();
        log.info("[Email] {}", email);
        message.setFrom(email);
        message.setTo(email);
        message.setSubject(event.toUpperCase());
        message.setText(name + sendMailService.content(event));
        emailSender.send(message);
    }
}
