package com.example.library.domain.book.service;

import com.example.library.domain.book.dto.BookDto;
import com.example.library.domain.book.dto.BookSimple;
import com.example.library.domain.book.entity.BookEntity;

public interface BookService {
    BookDto detailSearchByBookAuthor(String bookAuthor);
    BookDto detailSearchByBookName(String bookName);
    BookSimple simpleSearchByBookAuthor(String bookAuthor);
    BookSimple simpleSearchByBookName(String bookName);
    BookDto update(BookDto bookDto, Long bookCode);
    void delete(Long bookCode);
    BookDto add(BookDto bookDto);
}
