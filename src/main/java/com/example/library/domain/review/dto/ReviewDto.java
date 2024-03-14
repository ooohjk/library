package com.example.library.domain.review.dto;

import com.example.library.domain.review.entity.ReviewEntity;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ReviewDto {

    @NotNull
    private Long bookCode;

    @NotNull
    private Long userNo;

    @NotNull
    private String reviewContent;

    public ReviewDto(ReviewEntity review) {
        this.bookCode = review.getBook().getBookCode();
        this.userNo = review.getUser().getUserNo();
        this.reviewContent = review.getReviewContent();
    }

    public static ReviewDto info(ReviewEntity review) {
        return new ReviewDto(review);
    }
}
