package com.example.library.domain.rent.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rent_manager")
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

    public void setOverdueFlg(Boolean flg){
        this.overdueFlg=flg;
    }
}
