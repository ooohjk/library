package com.example.library.global.eventListener;

import com.example.library.global.mail.mailHistory.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class SendedMailEventHandler {

    private final MailService mailService;

    @Async
    @TransactionalEventListener(value = SendedMailEvent.class,phase = TransactionPhase.AFTER_COMMIT)
    public void handle(SendedMailEvent event){
        mailService.sendMailAndSave(event.getMailDto());
    }

}
