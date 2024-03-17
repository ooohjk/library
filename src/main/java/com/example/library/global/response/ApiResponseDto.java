package com.example.library.global.response;

import com.example.library.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.FieldError;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseDto<T> {
    public String code;
    public String msg;
    public T data;
    public List<ValidationError> errors;

    private ApiResponseDto(ErrorCode errorCode){
        this.code=errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    private ApiResponseDto(ErrorCode errorCode, T data) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
        this.data = data;
    }

    private ApiResponseDto(ErrorCode errorCode, @Nullable List<ValidationError> errors) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
        this.errors = errors;
    }


    public static <T> ApiResponseDto<T> createRes(ErrorCode errorCode, T data){
        return new ApiResponseDto<T>(errorCode,data);
    }

    public static <T> ApiResponseDto<T> createRes(ErrorCode errorCode){
        return new ApiResponseDto(errorCode);
    }

    public static <T> ApiResponseDto<T> createRes(ErrorCode errorCode,List<ValidationError> errors){
        return new ApiResponseDto(errorCode,errors);
    }

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class ValidationError{
        private final String field;
        private final String message;

        public static ValidationError of(String field,String message){
            return ValidationError.builder()
                    .field(field)
                    .message(message)
                    .build();
        }
    }

}
