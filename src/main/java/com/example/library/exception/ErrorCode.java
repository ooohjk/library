package com.example.library.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    SUC("0","OK"),

    //User
    USERID_DUPLICATED("1", "중복 아이디입니다. 아이디를 확인해주세요."),
    USERID_NOT_FOUND("2", "존재하지 않는 아이디입니다. 아이디를 확인해주세요."),
    PASSWORD_DIFFERNET("3", "올바르지 않은 패스워드입니다. 패스워드를 확인해주세요."),

    //Book
    BOOKNAME_NOT_FOUND("4",""),
    BOOKAUTHOR_NOT_FOUND("5",""),

    //Mail
    MAIL_NOT_FOUND("6", "존재하지 않는 메일주소입니다. 메일주소를 확인해주세요.")

    ;

    private String code;
    private String msg;
}
