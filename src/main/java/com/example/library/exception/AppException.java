package com.example.library.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class AppException extends RuntimeException {
    private ErrorCode errorCode;
    private String msg;

    public AppException(ErrorCode errorCode, String msg) {
        this.errorCode = errorCode;
        this.msg = msg;
        log.error(msg);
    }

    public AppException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        log.error(errorCode.getMsg());
    }


}
