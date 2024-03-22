package com.example.library.global.eventListener;

import com.example.library.global.mail.mailHistory.MailDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SendedMailEvent {
    private MailDto mailDto;

}
