package com.example.library.domain.book.controller;

import com.example.library.domain.book.dto.BookAddDto;
import com.example.library.domain.book.dto.BookDto;
import com.example.library.domain.book.dto.BookSimple;
import com.example.library.domain.book.dto.BookUpdateDto;
import com.example.library.domain.book.service.BookService;
import com.example.library.exception.ErrorCode;
import com.example.library.global.response.ApiResponseDto;
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
    public ApiResponseDto detailSearchByBookAuthor(@PathVariable("bookAuthor") String bookAuthor) {
        BookDto bookDto = bookService.detailSearchByBookAuthor(bookAuthor);
        return ApiResponseDto.createRes(ErrorCode.SUC, bookDto);
    }

    @GetMapping("/search/detail/bookName/{bookName}")
    public ApiResponseDto detailSearchByBookName(@PathVariable("bookName") String bookName) {
        BookDto bookDto = bookService.detailSearchByBookName(bookName);
        return ApiResponseDto.createRes(ErrorCode.SUC, bookDto);
    }

    @GetMapping("/search/simple/bookAuthor/{bookAuthor}")
    public ApiResponseDto simpleSearchByBookAuthor(@PathVariable("bookAuthor") String bookAuthor) {
        BookSimple bookSimple = bookService.simpleSearchByBookAuthor(bookAuthor);
        return ApiResponseDto.createRes(ErrorCode.SUC, bookSimple);
    }

    @GetMapping("/search/simple/bookName/{bookName}")
    public ApiResponseDto simpleSearchByBookName(@PathVariable("bookName") String bookName) {
        BookSimple bookSimple = bookService.simpleSearchByBookName(bookName);
        return ApiResponseDto.createRes(ErrorCode.SUC, bookSimple);
    }

    @PostMapping("/add")
    public ApiResponseDto add(@RequestBody BookAddDto bookAddDto) {
        BookDto bookDto = bookService.add(bookAddDto);
        return ApiResponseDto.createRes(ErrorCode.SUC, bookDto);
    }

    @PutMapping("/update/book/{bookCode}")
    public ApiResponseDto update(@RequestBody BookUpdateDto bookUpdateDto, @PathVariable("bookCode") Long bookCode) {
        BookDto bookDto = bookService.update(bookUpdateDto, bookCode);
        return ApiResponseDto.createRes(ErrorCode.SUC, bookDto);
    }

    @DeleteMapping("/delete/book/{bookCode}")
    public ApiResponseDto delete(@PathVariable("bookCode") Long bookCode) {
        bookService.delete(bookCode);
        return ApiResponseDto.createRes(ErrorCode.SUC);
    }
}
