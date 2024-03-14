package com.example.library.domain.book.dto;

import com.example.library.domain.book.entity.BookEntity;
import com.example.library.domain.review.dto.ReviewDto;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        this.bookState = book.getBookState();
        this.bookPublisher = book.getBookPublisher();
        this.isbn = book.getIsbn();
        this.pubDate = book.getPubDate();
        this.bookLocation = book.getBookLocation();
        this.bookImage = book.getBookImage();
        if (book.getReview() == null) {
            book.setReview(new ArrayList<>());
            this.review = book.getReview().stream()
                    .map(m -> new ReviewDto())
                    .collect(Collectors.toList());
        } else {
            this.review = book.getReview().stream()
                    .map(m -> new ReviewDto(m.getBook().getBookCode(), m.getUser().getUserNo(), m.getRegDate(), m.getReviewContent()))
                    .collect(Collectors.toList());
        }
    }

    public static BookDto detail(BookEntity book) {
        return new BookDto(book);
    }
}
