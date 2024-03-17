package com.example.library.exception;

import com.example.library.global.response.ApiResponseDto;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(AppException.class)
    public ApiResponseDto appExceptionHandler(AppException e) {
        return ApiResponseDto.createRes(e.getErrorCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponseDto validationExceptionHandler(MethodArgumentNotValidException e){
        List<ApiResponseDto.ValidationError> validationErrors =
                    e.getBindingResult().getFieldErrors().stream()
                    .map(fieldError-> ApiResponseDto.ValidationError.of(fieldError.getField(),fieldError.getDefaultMessage()))
                    .collect(Collectors.toList())
                ;
        return ApiResponseDto.createRes(ErrorCode.PARAMETER_FORMAT_INVALID,validationErrors);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ApiResponseDto validationExceptionHandler(MethodArgumentTypeMismatchException e){
        List<ApiResponseDto.ValidationError> validationErrors = Arrays.asList(e)
                        .stream()
                        .map(exception
                                ->ApiResponseDto.ValidationError.of(exception.getPropertyName(),
                                exception.getRequiredType().toString().substring(16) + " 타입이 필요합니다.")
                        )
                        .collect(Collectors.toList())
                ;
        return ApiResponseDto.createRes(ErrorCode.PARAMETER_FORMAT_INVALID,validationErrors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiResponseDto validationExceptionHandler(HttpMessageNotReadableException e){
        return ApiResponseDto.createRes(ErrorCode.PARAMETER_FIELD_INVALID);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ApiResponseDto validationExceptionHandler(ConstraintViolationException e){
        List<ApiResponseDto.ValidationError> validationErrors =
                e.getConstraintViolations().stream()
                        .map(fieldError-> ApiResponseDto.ValidationError.of(fieldError.getPropertyPath().toString(),fieldError.getMessage()))
                        .collect(Collectors.toList())
                ;
        return ApiResponseDto.createRes(ErrorCode.PARAMETER_FORMAT_INVALID,validationErrors);
    }

}
