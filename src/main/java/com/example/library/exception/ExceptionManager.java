package com.example.library.exception;

import com.example.library.global.response.ApiResponseDto;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(AppException.class)
    public ApiResponseDto appExceptionHandler(AppException e) {
        return ApiResponseDto.createRes(e.getErrorCode());
    }
}
