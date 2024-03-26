package com.example.library.domain.rent.infrastructure;

import com.example.library.domain.rent.RentState;
import com.example.library.domain.rent.domain.RentHistory;
import com.example.library.domain.rent.domain.RentRepository;
import com.example.library.domain.rent.infrastructure.entity.RentHistoryEntity;
import com.example.library.domain.rent.infrastructure.entity.RentManagerEntity;
import com.example.library.domain.rent.infrastructure.repository.RentRepositoryAdapter;
import com.example.library.domain.rent.infrastructure.repository.SpringDataJpaRentHistoryRepository;
import com.example.library.domain.rent.infrastructure.repository.SpringDataJpaRentManagerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //내 데이터베이스 쓰기
class RentHistoryRepositoryTest {

    @Autowired
    private SpringDataJpaRentHistoryRepository rentHistoryRepository;

    @Autowired
    private SpringDataJpaRentManagerRepository rentManagerRepository;



    @BeforeEach
    void setUp(){
        Long userNo= 1L;
        Long bookNo1 = 27L;
        Long bookNo2 = 28L;

        RentHistoryEntity entity1 = RentHistoryEntity.builder()
                        .userNo(userNo)
                        .bookNo(bookNo1)
                        .managerNo(111L)
                        .rentDt("20230326")
                        .haveToReturnDt("20230327")
                        .rentState(RentState.ON_RENT)
                .build()
                ;

        RentHistoryEntity entity2 = RentHistoryEntity.builder()
                .userNo(userNo)
                .bookNo(bookNo2)
                .managerNo(111L)
                .rentDt("20230326")
                .haveToReturnDt("20230327")
                .rentState(RentState.ON_RENT)
                .build()
                ;
        rentHistoryRepository.save(entity1);
        rentHistoryRepository.save(entity2);
    }

    @Test
    void 유저_대여현황_가져오기_Entity(){
        Long userNo= 1L;
        List<RentHistoryEntity> list = rentHistoryRepository.findByUserNoAndRentState(userNo,RentState.ON_RENT);
        Assertions.assertEquals(list.size(),2);
    }
}