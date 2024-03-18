package com.example.library.exception.exceptions;

import com.example.library.exception.AppException;
import com.example.library.exception.ErrorCode;

public class ExceedMaximumRentNumberException extends AppException {
    public ExceedMaximumRentNumberException(ErrorCode errorCode, String msg) {
        super(errorCode, msg);
    }

    public ExceedMaximumRentNumberException(ErrorCode errorCode) {
        super(errorCode);
    }
}
