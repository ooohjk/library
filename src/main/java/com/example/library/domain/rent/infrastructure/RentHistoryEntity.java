package com.example.library.domain.rent.infrastructure;

import com.example.library.domain.rent.RentState;
import com.example.library.global.utils.DateUtil;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity(name = "rent_history")
public class RentHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyNo;

    private Long managerNo;
//    private Long userNo;


    private Long bookNo;

    private String rentDt;

    private String returnDt;

    private String haveToReturnDt;

    private boolean extensionFlg;

    @Convert(converter = RentStateConverter.class)
    private RentState rentState;

    public RentHistoryEntity(Long managerNo, Long bookNo) {
        this.managerNo=managerNo;
        this.bookNo=bookNo;
        this.rentDt = DateUtil.getDate();
        this.haveToReturnDt = rentDt+6;  // 수정 필요
        this.extensionFlg = false;
        this.rentState=RentState.ON_RENT;
    }
}
