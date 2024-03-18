package com.example.library.exception.exceptions;

import com.example.library.exception.AppException;
import com.example.library.exception.ErrorCode;

public class OverdueUserException extends AppException {
    public OverdueUserException(ErrorCode errorCode, String msg) {
        super(errorCode, msg);
    }

    public OverdueUserException(ErrorCode errorCode) {
        super(errorCode);
    }
}
