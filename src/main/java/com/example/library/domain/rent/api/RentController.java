package com.example.library.domain.rent.api;

import com.example.library.domain.rent.application.RentService;
import com.example.library.domain.rent.application.dto.UserRentStatusResDto;
import com.example.library.exception.ErrorCode;
import com.example.library.global.response.ApiResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/rent/{userNo}/book/{bookNo}")
    public ApiResponseDto extendBook(@PathVariable("userNo")Long userNo , @PathVariable("bookNo") Long bookNo){
        rentService.extendBook(userNo,bookNo);
        return ApiResponseDto.createRes(ErrorCode.SUC);
    }

    @DeleteMapping("/rent/{userNo}/book/{bookNo}")
    public ApiResponseDto returnBook(@PathVariable("userNo")Long userNo , @PathVariable("bookNo") Long bookNo){
        rentService.returnBook(userNo,bookNo);
        return ApiResponseDto.createRes(ErrorCode.SUC);
    }

    @GetMapping("/rent/currentRentStatus/user/{userNo}")
    public ApiResponseDto getCurrentRentStatus(@PathVariable Long userNo){
        List<UserRentStatusResDto> lists = rentService.getCurrentRentStatus(userNo);
        return ApiResponseDto.createRes(ErrorCode.SUC,lists);
    }


}
