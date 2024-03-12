package com.example.library.exception.exceptions;

import com.example.library.exception.AppException;
import com.example.library.exception.ErrorCode;

public class BookNotFoundException extends AppException {

    public BookNotFoundException(ErrorCode errorCode, String msg) {
        super(errorCode, msg);
    }

    public BookNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
