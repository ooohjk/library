package com.example.library.domain.book.controller;

import com.example.library.domain.book.dto.BookAddDto;
import com.example.library.domain.book.dto.BookDto;
import com.example.library.domain.book.dto.BookSimpleDto;
import com.example.library.domain.book.service.BookService;
import com.example.library.exception.ErrorCode;
import com.example.library.global.response.ApiResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@Tag(name = "book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

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
        BookSimpleDto bookSimpleDto = bookService.simpleSearchByBookAuthor(bookAuthor);
        return ApiResponseDto.createRes(ErrorCode.SUC, bookSimpleDto);
    }

    @GetMapping("/search/simple/bookName/{bookName}")
    public ApiResponseDto simpleSearchByBookName(@PathVariable("bookName") String bookName) {
        BookSimpleDto bookSimpleDto = bookService.simpleSearchByBookName(bookName);
        return ApiResponseDto.createRes(ErrorCode.SUC, bookSimpleDto);
    }

    @PostMapping("/add")
    public ApiResponseDto add(@Valid @RequestBody BookAddDto bookAddDto) {
        BookAddDto bookAdd = bookService.add(bookAddDto);
        return ApiResponseDto.createRes(ErrorCode.SUC, bookAdd);
    }

    @PutMapping("/update/{bookCode}")
    public ApiResponseDto update(@Valid @RequestBody BookDto bookDto, @PathVariable("bookCode") Long bookCode) {
        BookDto book = bookService.update(bookDto, bookCode);
        return ApiResponseDto.createRes(ErrorCode.SUC, bookDto);
    }

    @DeleteMapping("/delete/{bookCode}")
    public ApiResponseDto delete(@PathVariable("bookCode") Long bookCode) {
        bookService.delete(bookCode);
        return ApiResponseDto.createRes(ErrorCode.SUC);
    }
}
