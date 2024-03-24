package com.example.library.global.mail.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@AllArgsConstructor
@Slf4j
public enum MailType implements MailContentInterface{
    MAIL_LOGIN("LOGIN","로그인 알림 발송") {
        @Override
        public String getContent(String userId, String contentForm) {
            return String.format(contentForm,userId);
        }
    },
    MAIL_JOIN("JOIN","회원 가입 알림 발송") {
        @Override
        public String getContent(String userId, String contentForm) {
            return String.format(contentForm,userId);
        }
    },
    MAIL_RENT("RENT","도서 대여 알림 발송") {
        @Override
        public String getContent(String userId, String contentForm) {
            return String.format(contentForm, userId, "도서명");
        }
    },
    MAIL_RETURN("RETURN","도서 반납 알림 발송") {
        @Override
        public String getContent(String userId, String contentForm) {
            return String.format(contentForm, userId, "도서명");
        }
    },
    MAIL_EXTEND("EXTEND","도서 연장 알림 발송") {
        @Override
        public String getContent(String userId, String contentForm) {
            return String.format(contentForm, userId, "도서명");
        }
    },
    MAIL_OVERDUE_REQ("OVERDUE_REQ","연체자 등록 알림 발송") {
        @Override
        public String getContent(String userId, String contentForm) {
            return String.format(contentForm, userId);
        }
    },
    MAIL_OVERDUE_REMOVE("OVERDUE_REMOVE","연체자 해제 알림 발송") {
        @Override
        public String getContent(String userId, String contentForm) {
            return String.format(contentForm, userId);
        }
    }
    ;


    private final String type;
    private final String subject;

}
