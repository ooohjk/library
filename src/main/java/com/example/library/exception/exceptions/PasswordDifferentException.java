package com.example.library.exception.exceptions;

import com.example.library.exception.AppException;
import com.example.library.exception.ErrorCode;

public class PasswordDifferentException extends AppException {

    public PasswordDifferentException(ErrorCode errorCode, String msg) {
        super(errorCode, msg);
    }

    public PasswordDifferentException(ErrorCode errorCode) {
        super(errorCode);
    }
}
