package com.example.library.domain.review.controller;

import com.example.library.domain.review.dto.ReviewDto;
import com.example.library.domain.review.dto.ReviewWriteDto;
import com.example.library.domain.review.service.ReviewService;
import com.example.library.exception.ErrorCode;
import com.example.library.global.response.ApiResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
@Tag(name = "review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/getAll")
    public ApiResponseDto getAllReview() {
        List<ReviewDto> reviewDtos = reviewService.getAllReview();
        return ApiResponseDto.createRes(ErrorCode.SUC, reviewDtos);
    }

    @PostMapping("/write/{bookCode}/{userId}")
    public ApiResponseDto write(@RequestBody ReviewWriteDto reviewWriteDto, @PathVariable("bookCode") Long bookCode, @PathVariable("userId") String userId) {
        ReviewDto reviewDto = reviewService.write(reviewWriteDto, bookCode, userId);
        return ApiResponseDto.createRes(ErrorCode.SUC, reviewDto);
    }
}
