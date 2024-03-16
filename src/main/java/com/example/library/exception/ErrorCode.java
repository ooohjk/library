package com.example.library.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    SUC("S00","SUCCESS"),

    //AutehnticationError
    AUTHENTICATION_INSUFFICIENT("A01","로그인 후 접근 바랍니다."),
    AUTHORITY_FAIL("A02","접근할 수 없는 권한입니다. 권한 확인 후 재접근 바랍니다."),

    //User
    USERID_DUPLICATED("U01", "중복 아이디입니다. 아이디를 확인해주세요."),
    USERID_NOT_FOUND("U02", "존재하지 않는 아이디입니다. 아이디를 확인해주세요."),
    PASSWORD_DIFFERNET("U03", "올바르지 않은 패스워드입니다. 패스워드를 확인해주세요."),
    USERNO_NOT_FOUND("U04", "존재하지 않는 유저번호입니다. 유저번호를 확인해주세요."),

    //Heart
    HEARTBOOK_EMPTY("H01","찜한 도서가 없습니다."),
    HEARTNO_NOT_FOUND("H02","존재하지 않는 찜번호입니다. 찜번호를 확인해주세요."),
    HEARTBOOK_ALREADY("H03","이미 찜한 도서입니다."),
    //Book
    BOOKNAME_NOT_FOUND("B01","존재하지 않는 책이름입니다. 다시 확인해주세요."),
    BOOKAUTHOR_NOT_FOUND("B02","존재하지 않는 저자입니다. 다시 확인해주세요."),
    BOOKCODE_NOT_FOUND("B03","존재하지 않는 책번호입니다. 다시 확인해주세요."),

    //Mail
    MAIL_NOT_FOUND("M01", "존재하지 않는 메일주소입니다. 메일주소를 확인해주세요."),

    //Review
    REVIEW_NOT_FOUND("R01", "해당도서의 리뷰가 없습니다.")

    ;

    private String code;
    private String msg;
}
