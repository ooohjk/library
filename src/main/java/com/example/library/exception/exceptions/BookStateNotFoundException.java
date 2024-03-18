package com.example.library.exception.exceptions;

import com.example.library.exception.AppException;
import com.example.library.exception.ErrorCode;

public class BookStateNotFoundException extends AppException {
    public BookStateNotFoundException(ErrorCode errorCode, String msg) {
        super(errorCode, msg);
    }

    public BookStateNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
