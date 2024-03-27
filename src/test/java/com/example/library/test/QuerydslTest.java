package com.example.library.test;

import com.example.library.config.QuerydslConfig;
import com.example.library.domain.rent.infrastructure.entity.RentHistoryEntity;
import com.example.library.global.mail.mailForm.entity.MailEntity;
import com.example.library.global.mail.mailForm.entity.QMailEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(QuerydslConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //내 데이터베이스 쓰기
public class QuerydslTest {

    @PersistenceContext
    EntityManager em;

    JPAQueryFactory jpaQueryFactory;

    @BeforeEach
    void init() {
        jpaQueryFactory = new JPAQueryFactory(em);

        MailEntity mailEntity = MailEntity.builder()
                        .mailContent("content")
                                .mailType("mailType")
                                        .build()
                ;

        em.persist(mailEntity);
    }

    @Test
    void 저장성공확인(){
        //given
//        QMailEntity mail = new QMailEntity("mail");
        QMailEntity mail = new QMailEntity("mailEntity");

        MailEntity mailEntity = jpaQueryFactory.selectFrom(mail)
                .from(mail)
                .where(mail.mailContent.eq("content"))
                .fetchOne()
                ;
        Assertions.assertEquals(mailEntity.getMailContent(),"content");

    }
}
