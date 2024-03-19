package com.example.library.domain.rent.manager.controller;

import com.example.library.domain.rent.manager.dto.RentManagerDto;
import com.example.library.domain.rent.manager.service.RentManagerService;
import com.example.library.exception.ErrorCode;
import com.example.library.global.response.ApiResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rent/manager")
@RequiredArgsConstructor
@Tag(name = "rentManager")
public class RentManagerController {

    private final RentManagerService rentManagerService;

    @GetMapping("/getAll")
    public ApiResponseDto getAllManager() {
        List<RentManagerDto> rentManagerDtoList = rentManagerService.getAllManager();
        return ApiResponseDto.createRes(ErrorCode.SUC, rentManagerDtoList);
    }
}
