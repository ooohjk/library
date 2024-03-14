package com.example.library.exception.exceptions;

import com.example.library.exception.AppException;
import com.example.library.exception.ErrorCode;

public class ReviewNotFoundException extends AppException {
    public ReviewNotFoundException(ErrorCode errorCode, String msg) {
        super(errorCode, msg);
    }

    public ReviewNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
