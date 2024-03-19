package com.example.library.domain.review.service.Impl;

import com.example.library.domain.book.entity.BookEntity;
import com.example.library.domain.book.enums.BookState;
import com.example.library.domain.review.entity.ReviewEntity;
import com.example.library.domain.review.repository.ReviewRepository;
import com.example.library.domain.user.entity.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class ReviewServiceImplTest {
    
    @Autowired
    ReviewRepository reviewRepository;
    
    @Test
    @DisplayName("리뷰 생성 시 리뷰날짜 자동생성 확인")
    void 리뷰작성시_작성일자_자동생성(){

        BookEntity book  =
                BookEntity.builder()
                        .bookCode(1L)
                        .bookName("테스트책1")
                        .isbn("1234")
                        .bookAuthor("저자1")
                        .bookImage("http://asd.com")
                        .bookContent("책 내용")
                        .bookPublisher("출판사1")
                        .bookLocation("A1")
                        .pubDate(new Date())
                        .bookState(BookState.RENT_AVAILABLE)
                        .build()
                ;
        UserEntity user = UserEntity.createOfficialUser()
                .userNo(11L)
                .userId("d")
                .userPwd("das")
                .userName("asd")
                .build()
                ;

        ReviewEntity reviewEntity = ReviewEntity
                .builder()
                .reviewContent("내용1")
                .book(book)
                .user(user)
                .build();
        reviewRepository.save(reviewEntity);
    }

}