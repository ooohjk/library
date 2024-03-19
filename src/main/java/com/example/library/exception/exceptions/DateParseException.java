package com.example.library.exception.exceptions;

import com.example.library.exception.AppException;
import com.example.library.exception.ErrorCode;

public class DateParseException extends AppException {
    public DateParseException(ErrorCode errorCode, String msg) {
        super(errorCode, msg);
    }

    public DateParseException(ErrorCode errorCode) {
        super(errorCode);
    }
}
