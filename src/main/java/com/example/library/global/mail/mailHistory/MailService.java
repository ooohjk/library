package com.example.library.global.mail.mailHistory;

import com.example.library.domain.user.entity.UserEntity;
import com.example.library.domain.user.repository.UserRepository;
import com.example.library.exception.ErrorCode;
import com.example.library.exception.exceptions.MailTypeNotFoundException;
import com.example.library.exception.exceptions.UserNotFoundException;
import com.example.library.global.mail.mailForm.entity.MailEntity;
import com.example.library.global.mail.mailForm.repository.MailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private final MailHistoryRepository mailHistoryRepository;
    private final UserRepository userRepository;
    private final MailRepository mailRepository;

    public MailHistoryEntity sendMailAndSave(MailDto mailDto){
        UserEntity selectedUser = userRepository.findByUserNo(mailDto.getUserNo()).orElseThrow(()->new UserNotFoundException(ErrorCode.USERNO_NOT_FOUND));
        MailEntity mailEntity = mailRepository.findByMailType(mailDto.getMailType().getType()).orElseThrow(()-> new MailTypeNotFoundException(ErrorCode.MAIL_TYPE_NOT_FOUND));

        String content = mailDto.getMailType().getContent(selectedUser.getUserId(),mailEntity.getMailContent());
        mailDto = new MailDto(mailDto.getUserNo(),
                selectedUser.getUserEmail(),
                mailDto.getMailType(),
                content,
                "O"
        );

        log.info(String.format("메일 발송 대상:[%s]/메일 타입:[%s]/발송내용: [%s]",mailDto.getEmail(),mailEntity.getMailType(),content));

        try{
            send(mailDto);
        }catch (MailException e){
            mailDto.setSendFailFlg();
        }

        return mailHistoryRepository.save(mailDto.toEntity());
    }

    private void send(MailDto mailDto){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(mailDto.getEmail());
        simpleMailMessage.setSubject(mailDto.getMailType().getSubject());
        simpleMailMessage.setText(mailDto.getContent());
        javaMailSender.send(simpleMailMessage);
    }

//    private String setContentByMailType(UserEntity user,String mailContentInDB, MailType mailType) {
//        if (MailType.MAIL_LOGIN.equals(mailType) || MailType.MAIL_JOIN.equals(mailType)) {
//            return String.format(mailContentInDB, user.getUserId());
//        } else if (MailType.MAIL_RENT.equals(mailType) || MailType.MAIL_RETURN.equals(mailType) || MailType.MAIL_EXTEND.equals(mailType)) {
//            return String.format(mailContentInDB, user.getUserId(), "도서명");
//        }
//        else{
//            throw new MailTypeNotFoundException(ErrorCode.MAIL_TYPE_NOT_FOUND);
//        }
//    }
}
