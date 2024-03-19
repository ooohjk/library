package com.example.library.exception.exceptions;

import com.example.library.exception.AppException;
import com.example.library.exception.ErrorCode;

public class ExtendNumberExceedException extends AppException {
    public ExtendNumberExceedException(ErrorCode errorCode, String msg) {
        super(errorCode, msg);
    }

    public ExtendNumberExceedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
