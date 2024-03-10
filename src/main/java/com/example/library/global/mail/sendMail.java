package com.example.library.global.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class sendMail {
    private static JavaMailSender emailSender;

    @Autowired
    public sendMail(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public static sendMail send(String event, String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email);
        message.setTo(email);
        message.setSubject(event.toUpperCase());
        message.setText(event.toUpperCase() + " " + email);
        emailSender.send(message);
        return null;
    }
}
