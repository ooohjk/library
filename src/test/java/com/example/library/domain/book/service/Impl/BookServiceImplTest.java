package com.example.library.domain.book.service.Impl;

import com.example.library.domain.book.domain.BookEntity;
import com.example.library.domain.book.enums.BookState;
import com.example.library.domain.book.infrastructure.SpringDataJpaBookRepository;
import com.example.library.global.utils.DateUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class BookServiceImplTest {

    @Autowired
    SpringDataJpaBookRepository bookRepository;

    @Test
    @DisplayName("도서 생성 시 도서날짜 자동기입")
    void 도더생성시_기입날짜_자동생성(){
        String uniqueIsbn = DateUtil.getDate()+DateUtil.getTime();

        BookEntity book  =
                BookEntity.builder()
                        .bookName("테스트책1")
                        .isbn(uniqueIsbn)
                        .bookAuthor("저자1")
                        .bookImage("http://asd.com")
                        .bookContent("책 내용")
                        .bookPublisher("출판사1")
                        .bookLocation("A1")
                        .pubDate(new Date())
                        .bookState(BookState.RENT_AVAILABLE)
                        .build()
                ;
        bookRepository.save(book);
    }
}