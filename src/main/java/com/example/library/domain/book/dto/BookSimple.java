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

    private BookSimple(BookEntity book) {
        this.bookName = book.getBookName();
        this.bookAuthor = book.getBookAuthor();
        this.bookState = book.getBookState();
        this.pubDate = book.getPubDate();
        this.bookImage = book.getBookImage();
    }

    public static BookSimple simple(BookEntity book) {
        return new BookSimple(book);
    }
}
