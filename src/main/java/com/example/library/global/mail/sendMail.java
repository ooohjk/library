package com.example.library.global.mail;

import com.example.library.domain.user.service.Impl.UserServiceImpl;
import com.example.library.send.service.SendMailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class sendMail {
    private static JavaMailSender emailSender;
    private static SendMailServiceImpl sendMailService;
    private static UserServiceImpl userService;

    @Autowired
    public sendMail(JavaMailSender emailSender, SendMailServiceImpl sendMailService, UserServiceImpl userService) {
        sendMail.emailSender = emailSender;
        sendMail.sendMailService = sendMailService;
        sendMail.userService = userService;
    }

    public static sendMail send(String event, String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email);
        message.setTo(email);
        message.setSubject(event.toUpperCase());
        message.setText(userService.getUserNameByEmail(email) + sendMailService.content(event));
        emailSender.send(message);
        return null;
    }
}
