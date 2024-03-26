package com.example.library.domain.book.service.Impl;

import com.example.library.domain.book.domain.BookEntity;
import com.example.library.domain.book.domain.repository.BookRepository;
import com.example.library.domain.book.enums.BookState;
import com.example.library.domain.book.service.BookService;
import com.example.library.domain.book.service.dto.BookAddDto;
import com.example.library.domain.book.service.dto.BookDto;
import com.example.library.domain.review.repository.ReviewRepository;
import com.example.library.domain.user.repository.HeartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;
    private final HeartRepository heartRepository;

//    @Override
//    @Transactional(readOnly = true)
//    public BookDto detailSearchByBookAuthor(String bookAuthor) {
//        BookEntity bookEntity = bookRepository.findByBookAuthor(bookAuthor)
//                .orElseThrow(() -> new BookNotFoundException(ErrorCode.BOOKAUTHOR_NOT_FOUND));
//
//        return BookDto.detail(bookEntity);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public BookDto detailSearchByBookName(String bookName) {
//        BookEntity bookEntity = bookRepository.findByBookName(bookName)
//                .orElseThrow(() -> new BookNotFoundException(ErrorCode.BOOKNAME_NOT_FOUND));
//
//        return BookDto.detail(bookEntity);
//    }
//
//    @Override
//    public BookSimpleDto simpleSearchByBookAuthor(String bookAuthor) {
//        BookEntity bookEntity = bookRepository.findByBookAuthor(bookAuthor)
//                .orElseThrow(() -> new BookNotFoundException(ErrorCode.BOOKAUTHOR_NOT_FOUND));
//
//        return BookSimpleDto.simple(bookEntity);
//    }
//
//    @Override
//    public BookSimpleDto simpleSearchByBookName(String bookName) {
//        BookEntity bookEntity = bookRepository.findByBookName(bookName)
//                .orElseThrow(() -> new BookNotFoundException(ErrorCode.BOOKNAME_NOT_FOUND));
//
//        return BookSimpleDto.simple(bookEntity);
//    }

    @Override
    @Transactional
    public BookAddDto add(BookAddDto bookAddDto) {
        BookEntity book = BookEntity.builder()
                .bookName(bookAddDto.getBookName())
                .bookAuthor(bookAddDto.getBookAuthor())
                .bookContent(bookAddDto.getBookContent())
                .bookState(BookState.getBookState(bookAddDto.getBookState()))
                .bookPublisher(bookAddDto.getBookPublisher())
                .isbn(bookAddDto.getIsbn())
                .pubDate(bookAddDto.getPubDate())
                .bookLocation(bookAddDto.getBookLocation())
                .bookImage(bookAddDto.getBookImage())
                .build();

        bookRepository.save(book);

        return BookAddDto.add(book);
    }

    @Override
    public BookDto update(BookDto bookDto, Long bookCode) {
        BookEntity bookEntity = inquiryBook(bookCode);

        bookEntity.setBookName(bookDto.getBookName());
        bookEntity.setBookAuthor(bookDto.getBookAuthor());
        bookEntity.setBookContent(bookDto.getBookContent());
        bookEntity.setBookState(BookState.getBookState(bookDto.getBookState()));
        bookEntity.setBookPublisher(bookDto.getBookPublisher());
        bookEntity.setIsbn(bookDto.getIsbn());
        bookEntity.setPubDate(bookDto.getPubDate());
        bookEntity.setBookLocation(bookDto.getBookLocation());
        bookEntity.setBookImage(bookDto.getBookImage());

        bookRepository.save(bookEntity);

        return BookDto.detail(bookEntity);
    }

//    @Override
//    @Transactional
//    public void delete(Long bookCode) {
//        reviewRepository.deleteByBookBookCode(bookCode);
//        heartRepository.deleteByBookBookCode(bookCode);
//        bookRepository.deleteByBookCode(bookCode);
//    }

    public BookEntity inquiryBook(Long bookNo){
        return bookRepository.findByBookNo(bookNo);
    }

    @Override
//    @Transactional //RentServiceImpl.rentBook 에서 트랜잭션 잡고 있으므로 패스
    public void rentSuc(Long bookNo){
        BookEntity selectedBook = inquiryBook(bookNo);
        selectedBook.rentSuc();
        log.info("도서 상태 변경 완료");
    }


    @Override
    public void returnSuc(Long bookNo){
        BookEntity selectedBook = inquiryBook(bookNo);
        selectedBook.returnSuc();
        log.info("도서 상태 변경 완료");
    }

}
