package com.example.library.domain.rent.history.controller;

import com.example.library.domain.rent.history.dto.RentHistoryDto;
import com.example.library.domain.rent.history.service.RentHistoryService;
import com.example.library.exception.ErrorCode;
import com.example.library.global.response.ApiResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/rent/history")
@Tag(name = "rentHistory")
@RequiredArgsConstructor
public class RentHistoryController {
    private final RentHistoryService rentHistoryService;

    @GetMapping("/getAll")
    public ApiResponseDto getAllRent() {
        List<RentHistoryDto> rentHistoryDtoList = rentHistoryService.getAllRent();
        return ApiResponseDto.createRes(ErrorCode.SUC, rentHistoryDtoList);
    }

    @GetMapping("/mylist/{userNo}")
    public ApiResponseDto getMylist(@PathVariable("userNo") Long userNo) {
        List<RentHistoryDto> rentHistoryDtoList = rentHistoryService.getMylist(userNo);
        return ApiResponseDto.createRes(ErrorCode.SUC, rentHistoryDtoList);
    }

    @PostMapping("/add/{userNo}/{bookCode}")
    public ApiResponseDto add(@PathVariable("userNo") Long userNo, @PathVariable("bookCode") Long bookCode) throws ParseException {
        RentHistoryDto rentHistoryDto = rentHistoryService.add(userNo, bookCode);
        return ApiResponseDto.createRes(ErrorCode.SUC, rentHistoryDto);
    }

    @PutMapping("/return/{userNo}/{bookCode}")
    public ApiResponseDto returnBook(@PathVariable("userNo") Long userNo, @PathVariable("bookCode") Long bookCode) throws ParseException {
        RentHistoryDto rentHistoryDto = rentHistoryService.returnBook(userNo, bookCode);
        return ApiResponseDto.createRes(ErrorCode.SUC, rentHistoryDto);
    }

    @PutMapping("/extension/{userNo}/{bookCode}")
    public ApiResponseDto extension(@PathVariable("userNo") Long userNo, @PathVariable("bookCode") Long bookCode) throws ParseException {
        RentHistoryDto rentHistoryDto = rentHistoryService.extension(userNo, bookCode);
        return ApiResponseDto.createRes(ErrorCode.SUC, rentHistoryDto);
    }
}
