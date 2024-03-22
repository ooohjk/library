package com.example.library.exception.exceptions;

import com.example.library.exception.AppException;
import com.example.library.exception.ErrorCode;

public class MailTypeNotFoundException extends AppException {

    public MailTypeNotFoundException(ErrorCode errorCode, String msg) {
        super(errorCode, msg);
    }

    public MailTypeNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
