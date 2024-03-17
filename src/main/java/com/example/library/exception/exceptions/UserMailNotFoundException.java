package com.example.library.exception.exceptions;

import com.example.library.exception.AppException;
import com.example.library.exception.ErrorCode;

public class UserMailNotFoundException extends AppException {

    public UserMailNotFoundException(ErrorCode errorCode, String msg) {
        super(errorCode, msg);
    }

    public UserMailNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
