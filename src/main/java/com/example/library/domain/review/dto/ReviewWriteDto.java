package com.example.library.domain.review.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ReviewWriteDto {
    @NotNull
    private String reviewContent;
}
