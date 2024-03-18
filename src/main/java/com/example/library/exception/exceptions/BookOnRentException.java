package com.example.library.exception.exceptions;

import com.example.library.exception.AppException;
import com.example.library.exception.ErrorCode;

public class BookOnRentException extends AppException {
    public BookOnRentException(ErrorCode errorCode, String msg) {
        super(errorCode, msg);
    }

    public BookOnRentException(ErrorCode errorCode) {
        super(errorCode);
    }
}
