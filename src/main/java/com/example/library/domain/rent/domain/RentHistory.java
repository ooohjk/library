package com.example.library.domain.rent.domain;

import com.example.library.domain.rent.RentState;
import com.example.library.domain.rent.infrastructure.RentHistoryEntity;
import lombok.Builder;

public class RentHistory {

    private Long historyNo;
    private Long managerNo;
//    private Long userNo;
    private Long bookNo;
    private String rentDt;
    private String returnDt;
    private String HaveToReturnDt;
    private boolean extensionFlg;
    private RentState rentState;

    @Builder(builderMethodName = "createRentHistory")
    public RentHistory(Long managerNo, Long bookNo) {
        this.managerNo = managerNo;
        this.bookNo = bookNo;
    }


    public RentHistoryEntity toEntity() {
        return new RentHistoryEntity(managerNo,bookNo);
    }
}
