package com.example.library.exception.exceptions;

import com.example.library.exception.AppException;
import com.example.library.exception.ErrorCode;

public class RentStateNotFoundException extends AppException {
    public RentStateNotFoundException(ErrorCode errorCode, String msg) {
        super(errorCode, msg);
    }

    public RentStateNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
