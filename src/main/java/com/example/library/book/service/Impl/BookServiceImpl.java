package com.example.library.book.service.Impl;

import com.example.library.book.dto.BookDto;
import com.example.library.book.dto.BookSimple;
import com.example.library.book.entity.BookEntity;
import com.example.library.book.repository.BookRepository;
import com.example.library.book.service.BookService;
import com.example.library.exception.AppException;
import com.example.library.exception.ErrorCode;
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
    public BookDto detailSearchByBookAuthor(String bookAuthor) {
        BookEntity bookEntity = bookRepository.searchByBookAuthor(bookAuthor)
                .orElseThrow(() -> new AppException(ErrorCode.BOOKAUTHOR_NOT_FOUND, bookAuthor + " 저자의 책이 없습니다."));

        BookDto bookDto = new BookDto(
                bookEntity.getBookName(),
                bookEntity.getBookAuthor(),
                bookEntity.getBookContent(),
                bookEntity.getBookState(),
                bookEntity.getBookPublisher(),
                bookEntity.getIsbn(),
                bookEntity.getPubDate(),
                bookEntity.getRegDate(),
                bookEntity.getBookLocation(),
                bookEntity.getBookImage()
        );
        return bookDto;
    }

    @Override
    public BookDto detailSearchByBookName(String bookName) {
        BookEntity bookEntity = bookRepository.searchByBookName(bookName)
                .orElseThrow(() -> new AppException(ErrorCode.BOOKNAME_NOT_FOUND, bookName + " 이라는 책은 없습니다."));

        BookDto bookDto = new BookDto(
                bookEntity.getBookName(),
                bookEntity.getBookAuthor(),
                bookEntity.getBookContent(),
                bookEntity.getBookState(),
                bookEntity.getBookPublisher(),
                bookEntity.getIsbn(),
                bookEntity.getPubDate(),
                bookEntity.getRegDate(),
                bookEntity.getBookLocation(),
                bookEntity.getBookImage()
        );
        return bookDto;
    }

    @Override
    public BookSimple simpleSearchByBookAuthor(String bookAuthor) {
        BookEntity bookEntity = bookRepository.searchByBookAuthor(bookAuthor)
                .orElseThrow(() -> new AppException(ErrorCode.BOOKAUTHOR_NOT_FOUND, bookAuthor + " 저자의 책이 없습니다."));

        BookSimple bookSimple = new BookSimple(
                bookEntity.getBookName(),
                bookEntity.getBookAuthor(),
                bookEntity.getBookState(),
                bookEntity.getPubDate(),
                bookEntity.getBookImage()
        );
        return bookSimple;
    }

    @Override
    public BookSimple simpleSearchByBookName(String bookName) {
        BookEntity bookEntity = bookRepository.searchByBookName(bookName)
                .orElseThrow(() -> new AppException(ErrorCode.BOOKNAME_NOT_FOUND, bookName + " 이라는 책은 없습니다."));

        BookSimple bookSimple = new BookSimple(
                bookEntity.getBookName(),
                bookEntity.getBookAuthor(),
                bookEntity.getBookState(),
                bookEntity.getPubDate(),
                bookEntity.getBookImage()
        );
        return bookSimple;
    }
}
