package com.example.library.book.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BookSimple {
    @NotNull
    private String bookName;

    @NotNull
    private String bookAuthor;

    @NotNull
    private Integer bookState;

    @NotNull
    private Date pubDate;

    private String bookImage;
}
