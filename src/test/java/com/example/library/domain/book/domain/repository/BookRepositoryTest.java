package com.example.library.domain.book.domain.repository;

import com.example.library.domain.book.domain.BookEntity;
import com.example.library.domain.book.enums.BookState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;

//@DataJpaTest // 테스트 시 JPA 관련 설정만 로드하기 때문에는 @Component, @Service, @Controller 등의 스프링 빈을 컨테이너에 등록하지 않아 테스트에서 주입을 받아서 사용할 수 없다.
@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Repository.class)) //위에서 말했듯이
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //내 데이터베이스 쓰기
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository ;

    @Test
    void 도서_저장and조회(){
        BookEntity bookEntity = BookEntity.builder()
                .bookAuthor("author")
                .bookContent("content")
                .bookImage("asd")
                .bookLocation("A1")
                .bookState(BookState.RENT_UNAVAILABLE)
                .bookPublisher("pub")
                .isbn("1234")
                .bookName("도서제목")
                .build()
                ;

        BookEntity savedBook = bookRepository.save(bookEntity);
        BookEntity selectedBook = bookRepository.findByBookNo(savedBook.getBookCode());

        Assertions.assertEquals(savedBook.getBookCode(),selectedBook.getBookCode());
    }
}