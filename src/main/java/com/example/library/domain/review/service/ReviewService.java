package com.example.library.domain.review.service;

import com.example.library.domain.review.dto.ReviewDto;
import com.example.library.domain.review.dto.ReviewWriteDto;

import java.util.List;

public interface ReviewService {
    List<ReviewDto> getAllReview();
    ReviewDto write(ReviewWriteDto reviewWriteDto, Long bookCode, String userId);
}
