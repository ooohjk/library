package com.example.library.domain.book.controller;

import com.example.library.domain.book.dto.BookAddDto;
import com.example.library.domain.book.dto.BookDto;
import com.example.library.domain.book.dto.BookSimple;
import com.example.library.domain.book.service.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@Tag(name = "book")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/search/detail/bookAuthor/{bookAuthor}")
    public BookDto detailSearchByBookAuthor(@PathVariable("bookAuthor") String bookAuthor) {
        return bookService.detailSearchByBookAuthor(bookAuthor);
    }

    @GetMapping("/search/detail/bookName/{bookName}")
    public BookDto detailSearchByBookName(@PathVariable("bookName") String bookName) {
        return bookService.detailSearchByBookName(bookName);
    }

    @GetMapping("/search/simple/bookAuthor/{bookAuthor}")
    public BookSimple simpleSearchByBookAuthor(@PathVariable("bookAuthor") String bookAuthor) {
        return bookService.simpleSearchByBookAuthor(bookAuthor);
    }

    @GetMapping("/search/simple/bookName/{bookName}")
    public BookSimple simpleSearchByBookName(@PathVariable("bookName") String bookName) {
        return bookService.simpleSearchByBookName(bookName);
    }

    @PostMapping("/add")
    public BookDto add(@RequestBody BookAddDto bookAddDto) {
        return bookService.add(bookAddDto);
    }

    @PutMapping("/update/book/{bookCode}")
    public BookDto update(@RequestBody BookDto bookDto, @PathVariable("bookCode") Long bookCode) {
        return bookService.update(bookDto, bookCode);
    }

    @DeleteMapping("/delete/book/{bookCode}")
    public void delete(@PathVariable("bookCode") Long bookCode) {
        bookService.delete(bookCode);
    }
}
