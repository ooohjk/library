package com.example.library.exception.exceptions;

import com.example.library.exception.AppException;
import com.example.library.exception.ErrorCode;

public class RentManagerNotFoudException extends AppException {
    public RentManagerNotFoudException(ErrorCode errorCode, String msg) {
        super(errorCode, msg);
    }

    public RentManagerNotFoudException(ErrorCode errorCode) {
        super(errorCode);
    }
}
