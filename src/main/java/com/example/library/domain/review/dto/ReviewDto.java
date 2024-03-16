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
    private String userId;

    @NotNull
    private String regDt;

    @NotNull
    private String regTm;

    @NotNull
    private String reviewContent;

    public ReviewDto(ReviewEntity review) {
        this.bookCode = review.getBook().getBookCode();
        this.userId = review.getUser() == null ? "unknown" : review.getUser().getUserId();
        this.regDt = review.getCreatedDt();
        this.regTm = review.getCreatedTm();
        this.reviewContent = review.getReviewContent();
    }

    public static ReviewDto info(ReviewEntity review) {
        return new ReviewDto(review);
    }
}
