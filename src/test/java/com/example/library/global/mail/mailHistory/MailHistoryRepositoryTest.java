package com.example.library.global.mail.mailHistory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //내장 디비가 아닌 mysql물리 db를 통한 테스트를 위해 추가
class MailHistoryRepositoryTest {

    @Autowired
    MailHistoryRepository mailHistoryRepository;

    @Test
    void mailSaveTest(){
        MailHistoryEntity mailHistoryEntity = MailHistoryEntity.builder()
                .userNo(1L)
                .content("content")
                .type("type")
                .email("sunghyun7895@naver.com")
                .build()
                ;

        MailHistoryEntity savedEntity = mailHistoryRepository.save(mailHistoryEntity);

        Assertions.assertEquals(savedEntity.getEmail(),mailHistoryEntity.getEmail());
    }
}