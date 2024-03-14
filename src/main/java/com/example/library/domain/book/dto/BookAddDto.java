package com.example.library.domain.book.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BookAddDto {
    @NotNull
    private String bookName;

    @NotNull
    private String bookAuthor;

    @NotNull
    private String bookContent;

    @NotNull
    private Integer bookState;

    @NotNull
    private String bookPublisher;

    @NotNull
    private String isbn;

    @NotNull
    private Date pubDate;

    @NotNull
    private String bookLocation;

    private String bookImage;
}
