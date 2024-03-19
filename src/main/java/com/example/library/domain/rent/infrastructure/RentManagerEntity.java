package com.example.library.domain.rent.infrastructure;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "rent_manager")
@NoArgsConstructor
@AllArgsConstructor
public class RentManagerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long managerNo;


    //주기가 같으므로 연관매핑으로 진행 @OneToOne
    private Long userNo; //유저 삭제 시 RentManager도 제거 개발 필요

    //알필요가 없어보임
    //    private List<RentHistory> rentHistoryList;

    private int currentRentNumber;

    private boolean overdueFlg;

    public RentManagerEntity(Long userNo,int initialRentNumber){
        this.userNo= userNo;
        this.currentRentNumber = initialRentNumber;
        this.overdueFlg=false;
    }
}
