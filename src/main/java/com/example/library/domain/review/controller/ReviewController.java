package com.example.library.domain.review.controller;

import com.example.library.domain.review.dto.ReviewDto;
import com.example.library.domain.review.dto.ReviewWriteDto;
import com.example.library.domain.review.service.ReviewService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
@Tag(name = "review")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/getAll")
    public List<ReviewDto> getAllReview() {
        return reviewService.getAllReview();
    }

    @PostMapping("/write/{bookCode}/{userNo}")
    public ReviewDto write(@RequestBody ReviewWriteDto reviewWriteDto, @PathVariable("bookCode") Long bookCode, @PathVariable("userNo") Long userNo) {
        return reviewService.write(reviewWriteDto, bookCode, userNo);
    }
}
