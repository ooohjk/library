package com.example.library.domain.rent.infrastructure;

import com.example.library.domain.rent.RentState;
import com.example.library.domain.rent.domain.RentManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //내 데이터베이스 쓰기
class RentHistoryRepositoryTest {

    @Autowired
    private RentHistoryRepository rentHistoryRepository;

    @Autowired
    private RentManagerRepository rentManagerRepository;



//    @BeforeEach
//    void setUp(){
//        Long managerNo= 111L;
//        Long bookNo = 27L;
//
//        rentHistoryRepository.save(new RentHistoryEntity(managerNo,bookNo));
//    }

//    @Test
//    void findBookOnRentOfUser(){
//        Long userNo= 227L;
//        RentManagerEntity rentManagerEntity = rentManagerRepository.getByUserNo(userNo).getManagerNo();
//
//        Long bookNo = 27L;
//        RentHistoryEntity selectedRentHistory = rentHistoryRepository.getByManagerNoAndBookNoAndRentState(rentManagerEntity.getManagerNo(),bookNo, RentState.ON_RENT);
//        Assertions.assertEquals(selectedRentHistory.getRentDt(),"20240319");
//    }
}