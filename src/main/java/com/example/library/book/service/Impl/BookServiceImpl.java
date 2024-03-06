package com.example.library.book.service.Impl;

import com.example.library.book.dto.BookDto;
import com.example.library.book.entity.BookEntity;
import com.example.library.book.repository.BookRepository;
import com.example.library.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDto searchByBookCode(Long bookCode) {
        BookEntity bookEntity = bookRepository.searchByBookCode(bookCode).get();
        BookDto bookDto = new BookDto(
                bookEntity.getBookName(),
                bookEntity.getBookAuthor(),
                bookEntity.getBookContent(),
                bookEntity.getBookState(),
                bookEntity.getBookPublisher(),
                bookEntity.getIsbn(),
                bookEntity.getPubDate(),
                bookEntity.getRegDate(),
                bookEntity.getBookLocation()
        );
        return bookDto;
    }
}
