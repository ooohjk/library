package com.example.library.domain.book.service;

import com.example.library.domain.book.dto.BookAddDto;
import com.example.library.domain.book.dto.BookDto;
import com.example.library.domain.book.dto.BookSimpleDto;
import com.example.library.domain.book.entity.BookEntity;

public interface BookService {
    BookDto detailSearchByBookAuthor(String bookAuthor);
    BookDto detailSearchByBookName(String bookName);
    BookSimpleDto simpleSearchByBookAuthor(String bookAuthor);
    BookSimpleDto simpleSearchByBookName(String bookName);
    BookDto update(BookDto bookDto, Long bookCode);
    void delete(Long bookCode);
    BookAddDto add(BookAddDto bookAddDto);
    BookEntity getBookDetail(Long bookCode);
}
