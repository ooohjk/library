package com.example.library.exception.exceptions;

import com.example.library.exception.AppException;
import com.example.library.exception.ErrorCode;

public class RentException extends AppException {

    public RentException(ErrorCode errorCode, String msg) {
        super(errorCode, msg);
    }

    public RentException(ErrorCode errorCode) {
        super(errorCode);
    }
}
