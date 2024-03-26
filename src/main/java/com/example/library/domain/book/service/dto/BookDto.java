package com.example.library.domain.book.service.dto;

import com.example.library.domain.book.domain.BookEntity;
import com.example.library.domain.review.dto.ReviewDto;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BookDto {

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

    private List<ReviewDto> review;

    private BookDto(BookEntity book) {
        this.bookName = book.getBookName();
        this.bookAuthor = book.getBookAuthor();
        this.bookContent = book.getBookContent();
        this.bookState = book.getBookState().getStateNum();
        this.bookPublisher = book.getBookPublisher();
        this.isbn = book.getIsbn();
        this.pubDate = book.getPubDate();
        this.bookLocation = book.getBookLocation();
        this.bookImage = book.getBookImage();
        this.review = book.getReview().stream()
                .map(ReviewDto::info)
                .collect(Collectors.toList());
    }

    public static BookDto detail(BookEntity book) {
        return new BookDto(book);
    }
}
