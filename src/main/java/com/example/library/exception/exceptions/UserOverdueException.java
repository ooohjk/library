package com.example.library.exception.exceptions;

import com.example.library.exception.AppException;
import com.example.library.exception.ErrorCode;

public class UserOverdueException extends AppException {

    public UserOverdueException(ErrorCode errorCode, String msg) {
        super(errorCode, msg);
    }

    public UserOverdueException(ErrorCode errorCode) {
        super(errorCode);
    }
}
