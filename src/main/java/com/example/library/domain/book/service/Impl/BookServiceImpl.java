package com.example.library.domain.book.service.Impl;

import com.example.library.domain.book.dto.BookAddDto;
import com.example.library.domain.book.dto.BookDto;
import com.example.library.domain.book.dto.BookSimple;
import com.example.library.domain.book.dto.BookUpdateDto;
import com.example.library.domain.book.entity.BookEntity;
import com.example.library.domain.book.repository.BookRepository;
import com.example.library.domain.book.service.BookService;
import com.example.library.domain.review.entity.ReviewEntity;
import com.example.library.domain.review.repository.ReviewRepository;
import com.example.library.domain.user.repository.HeartRepository;
import com.example.library.exception.ErrorCode;
import com.example.library.exception.exceptions.BookNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;
    private final HeartRepository heartRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, ReviewRepository reviewRepository, HeartRepository heartRepository) {
        this.bookRepository = bookRepository;
        this.reviewRepository = reviewRepository;
        this.heartRepository = heartRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public BookDto detailSearchByBookAuthor(String bookAuthor) {
        BookEntity bookEntity = bookRepository.findByBookAuthor(bookAuthor)
                .orElseThrow(() -> new BookNotFoundException(ErrorCode.BOOKAUTHOR_NOT_FOUND));

        return BookDto.detail(bookEntity);
    }

    @Override
    @Transactional(readOnly = true)
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
    @Transactional
    public BookDto add(BookAddDto bookAddDto) {
        BookEntity book = BookEntity.builder()
                .bookName(bookAddDto.getBookName())
                .bookAuthor(bookAddDto.getBookAuthor())
                .bookContent(bookAddDto.getBookContent())
                .bookState(bookAddDto.getBookState())
                .bookPublisher(bookAddDto.getBookPublisher())
                .isbn(bookAddDto.getIsbn())
                .pubDate(bookAddDto.getPubDate())
                .bookLocation(bookAddDto.getBookLocation())
                .bookImage(bookAddDto.getBookImage())
                .build();

        bookRepository.save(book);

        return BookDto.detail(book);
    }

    @Override
    public BookDto update(BookUpdateDto bookUpdateDto, Long bookCode) {
        BookEntity bookEntity = getBookDetail(bookCode);

        bookEntity.setBookName(bookUpdateDto.getBookName());
        bookEntity.setBookAuthor(bookUpdateDto.getBookAuthor());
        bookEntity.setBookContent(bookUpdateDto.getBookContent());
        bookEntity.setBookState(bookUpdateDto.getBookState());
        bookEntity.setBookPublisher(bookUpdateDto.getBookPublisher());
        bookEntity.setIsbn(bookUpdateDto.getIsbn());
        bookEntity.setPubDate(bookUpdateDto.getPubDate());
        bookEntity.setBookLocation(bookUpdateDto.getBookLocation());
        bookEntity.setBookImage(bookUpdateDto.getBookImage());

        bookRepository.save(bookEntity);

        return BookDto.detail(bookEntity);
    }

    @Override
    @Transactional
    public void delete(Long bookCode) {
        reviewRepository.deleteByBookBookCode(bookCode);
        heartRepository.deleteByBookBookCode(bookCode);
        bookRepository.deleteByBookCode(bookCode);
    }

    public BookEntity getBookDetail(Long bookCode){
        return bookRepository.findByBookCode(bookCode)
                .orElseThrow(() -> new BookNotFoundException(ErrorCode.BOOKCODE_NOT_FOUND));
    }
}
