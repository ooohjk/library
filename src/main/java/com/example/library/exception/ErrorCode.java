package com.example.library.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    USERID_DUPLICATED(HttpStatus.CONFLICT, ""),
    USERID_NOT_FOUND(HttpStatus.NOT_FOUND, ""),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, ""),
    BOOKNAME_NOT_FOUND(HttpStatus.NOT_FOUND, ""),
    BOOKAUTHOR_NOT_FOUND(HttpStatus.NOT_FOUND, ""),
    MAIL_NOT_FOUND(HttpStatus.NOT_FOUND, "")
    ;

    private HttpStatus httpStatus;
    private String message;
}
