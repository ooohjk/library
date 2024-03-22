package com.example.library.global.mail.mailForm.service;

import com.example.library.exception.AppException;
import com.example.library.exception.ErrorCode;
import com.example.library.global.mail.mailForm.repository.MailRepository;
import com.example.library.global.mail.mailForm.dto.SendMailResDto;
import com.example.library.global.mail.mailForm.entity.MailEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendMailServiceImpl {
    private final MailRepository mailRepository;

    public String content(String type) {
        MailEntity mailEntity = mailRepository.findByMailType(type)
                .orElseThrow(() -> new AppException(ErrorCode.MAIL_NOT_FOUND));

        SendMailResDto sendMailResDto = new SendMailResDto(mailEntity.getMailContent());
        return sendMailResDto.getMailContent();
    }
}
