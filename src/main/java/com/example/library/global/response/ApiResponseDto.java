package com.example.library.global.response;

import com.example.library.exception.ErrorCode;

public class ApiResponseDto<T> {
    public String code;
    public String msg;
    public T data;

    private ApiResponseDto(ErrorCode errorCode, T data) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
        this.data = data;
    }

    private ApiResponseDto(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public static <T> ApiResponseDto<T> createRes(ErrorCode errorCode, T data){
        return new ApiResponseDto<T>(errorCode,data);
    }

    public static <T> ApiResponseDto<T> createRes(ErrorCode errorCode){
        return new ApiResponseDto(errorCode);
    }
}
