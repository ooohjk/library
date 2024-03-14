package com.example.library.domain.review.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ReviewWriteDto {
    @NotNull
    private Date regDate;

    @NotNull
    private String reviewContent;
}
