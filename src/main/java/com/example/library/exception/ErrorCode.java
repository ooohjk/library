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
    USERNO_NOT_FOUND("3", "존재하지 않는 유저번호입니다. 번호를 확인해주세요."),
    PASSWORD_DIFFERNET("4", "올바르지 않은 패스워드입니다. 패스워드를 확인해주세요."),

    //Book
    BOOKNAME_NOT_FOUND("5","존재하지 않는 책이름입니다. 다시 확인해주세요."),
    BOOKAUTHOR_NOT_FOUND("6","존재하지 않는 저자입니다. 다시 확인해주세요."),
    BOOKCODE_NOT_FOUND("7","존재하지 않는 책번호입니다. 다시 확인해주세요."),

    //Mail
    MAIL_NOT_FOUND("8", "존재하지 않는 메일주소입니다. 메일주소를 확인해주세요."),

    //Review
    REVIEW_NOT_FOUND("9", "해당도서의 리뷰가 없습니다.")

    ;

    private String code;
    private String msg;
}
