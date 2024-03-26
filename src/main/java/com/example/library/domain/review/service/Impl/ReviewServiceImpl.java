package com.example.library.domain.review.service.Impl;

import com.example.library.domain.book.infrastructure.SpringDataJpaBookRepository;
import com.example.library.domain.review.dto.ReviewDto;
import com.example.library.domain.review.dto.ReviewWriteDto;
import com.example.library.domain.review.entity.ReviewEntity;
import com.example.library.domain.review.repository.ReviewRepository;
import com.example.library.domain.review.service.ReviewService;
import com.example.library.domain.user.repository.UserRepository;
import com.example.library.exception.ErrorCode;
import com.example.library.exception.exceptions.BookNotFoundException;
import com.example.library.exception.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final SpringDataJpaBookRepository bookRepository;

    @Override
    public List<ReviewDto> getAllReview() {
        List<ReviewEntity> review = reviewRepository.findAll();

        return review.stream()
                .map(m -> new ReviewDto(m.getBook().getBookCode(), m.getUser() == null ? "unknown" : m.getUser().getUserId(), m.getCreatedDt(), m.getCreatedDt(), m.getReviewContent()))
                .collect(Collectors.toList());
    }

    @Override
    public ReviewDto write(ReviewWriteDto reviewWriteDto, Long bookCode, String userId) {
        ReviewEntity review = ReviewEntity.builder()
                .book(bookRepository.findByBookCode(bookCode)
                        .orElseThrow(() -> new BookNotFoundException(ErrorCode.BOOKCODE_NOT_FOUND)))
                .user(userRepository.findByUserId(userId)
                        .orElseThrow(() -> new UserNotFoundException(ErrorCode.USERNO_NOT_FOUND)))
                .reviewContent(reviewWriteDto.getReviewContent())
                .build();
        reviewRepository.save(review);

        return ReviewDto.info(review);
    }
}
