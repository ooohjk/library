package com.example.library.exception.exceptions;

import com.example.library.exception.AppException;
import com.example.library.exception.ErrorCode;

public class UserMailSendFailException extends AppException {

    public UserMailSendFailException(ErrorCode errorCode, String msg) {
        super(errorCode, msg);
    }

    public UserMailSendFailException(ErrorCode errorCode) {
        super(errorCode);
    }
}
