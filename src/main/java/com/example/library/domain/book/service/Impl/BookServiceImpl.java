package com.example.library.domain.book.service.Impl;

import com.example.library.domain.book.dto.BookAddDto;
import com.example.library.domain.book.dto.BookDto;
import com.example.library.domain.book.dto.BookSimpleDto;
import com.example.library.domain.book.entity.BookEntity;
import com.example.library.domain.book.enums.BookState;
import com.example.library.domain.book.repository.BookRepository;
import com.example.library.domain.book.service.BookService;
import com.example.library.domain.review.repository.ReviewRepository;
import com.example.library.domain.user.repository.HeartRepository;
import com.example.library.exception.ErrorCode;
import com.example.library.exception.exceptions.BookNotFoundException;
import com.example.library.exception.exceptions.BookOnRentException;
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
    public BookSimpleDto simpleSearchByBookAuthor(String bookAuthor) {
        BookEntity bookEntity = bookRepository.findByBookAuthor(bookAuthor)
                .orElseThrow(() -> new BookNotFoundException(ErrorCode.BOOKAUTHOR_NOT_FOUND));

        return BookSimpleDto.simple(bookEntity);
    }

    @Override
    public BookSimpleDto simpleSearchByBookName(String bookName) {
        BookEntity bookEntity = bookRepository.findByBookName(bookName)
                .orElseThrow(() -> new BookNotFoundException(ErrorCode.BOOKNAME_NOT_FOUND));

        return BookSimpleDto.simple(bookEntity);
    }

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
        BookEntity bookEntity = getBookDetail(bookCode);

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

    @Override
    @Transactional
    public void delete(Long bookCode) {
        reviewRepository.deleteByBookBookCode(bookCode);
        heartRepository.deleteByBookBookCode(bookCode);
        bookRepository.deleteByBookCode(bookCode);
    }

    public BookEntity getBookDetail(Long bookNo){
        log.info(String.format("도서 조회 요청 - bookNo[%s]",bookNo.toString()));

        return bookRepository.findByBookCode(bookNo)
                .orElseThrow(() -> new BookNotFoundException(ErrorCode.BOOKCODE_NOT_FOUND));
    }

    @Override
    public void checkRentAvailable(Long bookNo) {
        log.info("[checkRentAvailable] 도서 대여 가능 여부 확인");

        BookEntity selectedBook = getBookDetail(bookNo);

        if(!selectedBook.isRentAvailableBook()){
            log.error(String.format("해당 도서[%s]는 현재 대여 중인 도서입니다. 대여 상태 [%s]",
                    bookNo.toString(),
                    "대여 중")
            );
            throw new BookOnRentException(ErrorCode.BOOK_ON_RENT);
        }

        log.info(String.format("해당 도서[%s]는 대여 가능 도서입니다. 대여 상태 [%s]", bookNo.toString(),"사내 배치"));
        log.info("[checkRentAvailable] 도서 대여 가능");
    }

    @Override
//    @Transactional //RentServiceImpl.rentBook 에서 트랜잭션 잡고 있으므로 패스
    public void rentSuc(Long bookNo){
        BookEntity selectedBook = getBookDetail(bookNo);
        selectedBook.rentSuc();

        log.info("도서 상태 변경 완료");
    }

}
