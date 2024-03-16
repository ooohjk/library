package com.example.library.domain.book.dto;

import com.example.library.domain.book.entity.BookEntity;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BookSimpleDto {
    @NotNull
    private String bookName;

    @NotNull
    private String bookAuthor;

    @NotNull
    private Integer bookState;

    @NotNull
    private Date pubDate;

    private String bookImage;

    private BookSimpleDto(BookEntity book) {
        this.bookName = book.getBookName();
        this.bookAuthor = book.getBookAuthor();
        this.bookState = book.getBookState();
        this.pubDate = book.getPubDate();
        this.bookImage = book.getBookImage();
    }

    public static BookSimpleDto simple(BookEntity book) {
        return new BookSimpleDto(book);
    }
}
