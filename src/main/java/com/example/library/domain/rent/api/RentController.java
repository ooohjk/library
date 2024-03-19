package com.example.library.domain.rent.api;

import com.example.library.domain.rent.application.RentService;
import com.example.library.exception.ErrorCode;
import com.example.library.global.response.ApiResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RentController {

    private final RentService rentService;

    @PostMapping("/rent/{userNo}/book/{bookNo}")
    public ApiResponseDto rentBook(@PathVariable("userNo")Long userNo , @PathVariable("bookNo") Long bookNo){
        rentService.rentBook(userNo,bookNo);
        return ApiResponseDto.createRes(ErrorCode.SUC);
    }

    @DeleteMapping("/rent/{userNo}/book/{bookNo}")
    public ApiResponseDto returnBook(@PathVariable("userNo")Long userNo , @PathVariable("bookNo") Long bookNo){
        rentService.returnBook(userNo,bookNo);
        return ApiResponseDto.createRes(ErrorCode.SUC);
    }
}
