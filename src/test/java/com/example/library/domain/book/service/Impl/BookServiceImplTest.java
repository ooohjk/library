package com.example.library.domain.book.service.Impl;

import com.example.library.domain.book.entity.BookEntity;
import com.example.library.domain.book.repository.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class BookServiceImplTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    @DisplayName("도서 생성 시 도서날짜 자동기입")
    void 도더생성시_기입날짜_자동생성(){
        BookEntity book  =
                BookEntity.builder()
                        .bookName("테스트책1")
                        .isbn("1234")
                        .bookAuthor("저자1")
                        .bookImage("http://asd.com")
                        .bookContent("책 내용")
                        .bookPublisher("출판사1")
                        .bookLocation("A1")
                        .pubDate(new Date())
                        .bookState(1)
                        .build()
                ;
        bookRepository.save(book);
    }
}