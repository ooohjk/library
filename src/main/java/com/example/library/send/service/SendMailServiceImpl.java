package com.example.library.send.service;

import com.example.library.exception.ErrorCode;
import com.example.library.exception.exceptions.UserMailSendFailException;
import com.example.library.send.dto.SendMailResDto;
import com.example.library.send.entity.SendMailEntity;
import com.example.library.send.repository.SendMailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendMailServiceImpl {
    private final SendMailRepository sendMailRepository;

    public String content(String type) {
        SendMailEntity sendMailEntity = sendMailRepository.findByMailType(type)
                .orElseThrow(() -> new UserMailSendFailException(ErrorCode.MAIL_NOT_FOUND));

        SendMailResDto sendMailResDto = new SendMailResDto(sendMailEntity.getMailContent());
        return sendMailResDto.getMailContent();
    }
}
