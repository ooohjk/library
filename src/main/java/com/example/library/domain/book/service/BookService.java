package com.example.library.domain.book.service;

import com.example.library.domain.book.dto.BookDto;
import com.example.library.domain.book.dto.BookSimple;

public interface BookService {
    BookDto detailSearchByBookAuthor(String bookAuthor);
    BookDto detailSearchByBookName(String bookName);
    BookSimple simpleSearchByBookAuthor(String bookAuthor);
    BookSimple simpleSearchByBookName(String bookName);
}
