package com.example.library.domain.book.dto;

import com.example.library.domain.book.entity.BookEntity;
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

    private BookAddDto(BookEntity book) {
        this.bookName = book.getBookName();
        this.bookAuthor = book.getBookAuthor();
        this.bookContent = book.getBookContent();
        this.bookState = book.getBookState().getStateNum();
        this.bookPublisher = book.getBookPublisher();
        this.isbn = book.getIsbn();
        this.pubDate = book.getPubDate();
        this.bookLocation = book.getBookLocation();
        this.bookImage = book.getBookImage();
    }

    public static BookAddDto add(BookEntity book) {
        return new BookAddDto(book);
    }
}
