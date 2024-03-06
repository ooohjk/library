package com.example.library.book.service;

import com.example.library.book.dto.BookDto;

public interface BookService {
    BookDto searchByBookCode(Long bookCode);
}
