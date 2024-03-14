package com.example.library.exception.exceptions;

import com.example.library.exception.AppException;
import com.example.library.exception.ErrorCode;

public class HeartBookNotFoundException extends AppException {
    public HeartBookNotFoundException(ErrorCode errorCode, String msg) {
        super(errorCode,msg);
    }

    public HeartBookNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
