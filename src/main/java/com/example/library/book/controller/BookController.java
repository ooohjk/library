package com.example.library.book.controller;

import com.example.library.book.dto.BookDto;
import com.example.library.book.dto.BookSimple;
import com.example.library.book.service.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
@Tag(name = "book")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/search/detail/bookAuthor")
    public BookDto detailSearchByBookAuthor(String bookAuthor) {
        return bookService.detailSearchByBookAuthor(bookAuthor);
    }

    @GetMapping("/search/detail/bookName")
    public BookDto detailSearchByBookName(String bookName) {
        return bookService.detailSearchByBookName(bookName);
    }

    @GetMapping("/search/simple/bookAuthor")
    public BookSimple simpleSearchByBookAuthor(String bookAuthor) {
        return bookService.simpleSearchByBookAuthor(bookAuthor);
    }

    @GetMapping("/search/simple/bookName")
    public BookSimple simpleSearchByBookName(String bookName) {
        return bookService.simpleSearchByBookName(bookName);
    }
}
