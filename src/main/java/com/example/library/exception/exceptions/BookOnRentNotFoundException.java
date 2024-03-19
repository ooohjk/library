package com.example.library.exception.exceptions;

import com.example.library.exception.AppException;
import com.example.library.exception.ErrorCode;

public class BookOnRentNotFoundException extends AppException {
    public BookOnRentNotFoundException(ErrorCode errorCode, String msg) {
        super(errorCode, msg);
    }

    public BookOnRentNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
