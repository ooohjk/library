package com.example.library.exception.exceptions;

import com.example.library.exception.AppException;
import com.example.library.exception.ErrorCode;

public class HeartBookAlreadyException extends AppException {
    public HeartBookAlreadyException(ErrorCode errorCode, String msg) {
        super(errorCode,msg);
    }

    public HeartBookAlreadyException(ErrorCode errorCode) {
        super(errorCode);
    }
}
