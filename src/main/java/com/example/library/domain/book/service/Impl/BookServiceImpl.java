package com.example.library.domain.book.service.Impl;

import com.example.library.domain.book.dto.BookDto;
import com.example.library.domain.book.dto.BookSimple;
import com.example.library.domain.book.entity.BookEntity;
import com.example.library.domain.book.repository.BookRepository;
import com.example.library.domain.book.service.BookService;
import com.example.library.exception.ErrorCode;
import com.example.library.exception.exceptions.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDto detailSearchByBookAuthor(String bookAuthor) {
        BookEntity bookEntity = bookRepository.findByBookAuthor(bookAuthor)
                .orElseThrow(() -> new BookNotFoundException(ErrorCode.BOOKAUTHOR_NOT_FOUND));

        return BookDto.detail(bookEntity);
    }

    @Override
    public BookDto detailSearchByBookName(String bookName) {
        BookEntity bookEntity = bookRepository.findByBookName(bookName)
                .orElseThrow(() -> new BookNotFoundException(ErrorCode.BOOKNAME_NOT_FOUND));

        return BookDto.detail(bookEntity);
    }

    @Override
    public BookSimple simpleSearchByBookAuthor(String bookAuthor) {
        BookEntity bookEntity = bookRepository.findByBookAuthor(bookAuthor)
                .orElseThrow(() -> new BookNotFoundException(ErrorCode.BOOKAUTHOR_NOT_FOUND));

        return BookSimple.simple(bookEntity);
    }

    @Override
    public BookSimple simpleSearchByBookName(String bookName) {
        BookEntity bookEntity = bookRepository.findByBookName(bookName)
                .orElseThrow(() -> new BookNotFoundException(ErrorCode.BOOKNAME_NOT_FOUND));

        return BookSimple.simple(bookEntity);
    }

    @Override
    public BookDto add(BookDto bookDto) {
        BookEntity book = BookEntity.builder()
                .bookName(bookDto.getBookName())
                .bookAuthor(bookDto.getBookAuthor())
                .bookContent(bookDto.getBookContent())
                .bookState(bookDto.getBookState())
                .bookPublisher(bookDto.getBookPublisher())
                .isbn(bookDto.getIsbn())
                .pubDate(bookDto.getPubDate())
                .regDate(bookDto.getRegDate())
                .bookLocation(bookDto.getBookLocation())
                .bookImage(bookDto.getBookImage())
                .build();

        bookRepository.save(book);

        return BookDto.detail(book);
    }

    @Override
    public BookDto update(BookDto bookDto, Long bookCode) {
        BookEntity bookEntity = bookRepository.findByBookCode(bookCode)
                .orElseThrow(() -> new BookNotFoundException(ErrorCode.BOOKCODE_NOT_FOUND));

        bookEntity.setBookName(bookDto.getBookName());
        bookEntity.setBookAuthor(bookDto.getBookAuthor());
        bookEntity.setBookContent(bookDto.getBookContent());
        bookEntity.setBookState(bookDto.getBookState());
        bookEntity.setBookPublisher(bookDto.getBookPublisher());
        bookEntity.setIsbn(bookDto.getIsbn());
        bookEntity.setPubDate(bookDto.getPubDate());
        bookEntity.setRegDate(bookDto.getRegDate());
        bookEntity.setBookLocation(bookDto.getBookLocation());
        bookEntity.setBookImage(bookDto.getBookImage());

        bookRepository.save(bookEntity);

        return BookDto.detail(bookEntity);
    }

    @Override
    @Transactional
    public void delete(Long bookCode) {
        bookRepository.deleteByBookCode(bookCode);
    }
}
