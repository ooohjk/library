package com.example.library.exception.exceptions;

import com.example.library.exception.AppException;
import com.example.library.exception.ErrorCode;

public class UserNotFoundException extends AppException {

    public UserNotFoundException(ErrorCode errorCode, String msg) {
        super(errorCode,msg);
    }

    public UserNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
