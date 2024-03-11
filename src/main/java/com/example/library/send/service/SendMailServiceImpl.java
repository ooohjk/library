package com.example.library.send.service;

import com.example.library.exception.AppException;
import com.example.library.exception.ErrorCode;
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
                .orElseThrow(() -> new AppException(ErrorCode.MAIL_NOT_FOUND, type + "의 도메인이 없습니다."));

        SendMailResDto sendMailResDto = new SendMailResDto(sendMailEntity.getMailContent());
        return sendMailResDto.getMailContent();
    }
}
