package com.example.library.exception.exceptions;

import com.example.library.exception.AppException;
import com.example.library.exception.ErrorCode;

public class UserIdDuplicateException extends AppException {

    public UserIdDuplicateException(ErrorCode errorCode, String msg) {
        super(errorCode,msg);
    }

    public UserIdDuplicateException(ErrorCode errorCode) {
        super(errorCode);
    }
}
