package com.example.library.domain.rent.controller;

import com.example.library.domain.rent.dto.RentDto;
import com.example.library.domain.rent.service.RentService;
import com.example.library.exception.ErrorCode;
import com.example.library.global.response.ApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rent")
@RequiredArgsConstructor
public class RentController {
    private final RentService rentService;

    @GetMapping("/")
    public ApiResponseDto getAllRent() {
        List<RentDto> rentDtoList = rentService.getAllRent();
        return ApiResponseDto.createRes(ErrorCode.SUC, rentDtoList);
    }
}
