package com.example.library.domain.rent.domain;

import com.example.library.domain.rent.RentState;
import com.example.library.domain.rent.infrastructure.entity.RentHistoryEntity;
import com.example.library.global.utils.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Builder
@AllArgsConstructor
public class RentHistory {

    @Builder(builderMethodName = "createRentHistory",builderClassName = "createRentHistory")
    public RentHistory(Long managerNo,Long userNo, Long bookNo) {
        this.managerNo=managerNo;
        this.userNo=userNo;
        this.bookNo=bookNo;
        this.rentDt = DateUtil.getDate();
        this.haveToReturnDt = rentDt+6;  // 수정 필요
        this.extensionFlg = false;
        this.rentState=RentState.ON_RENT;
    }

    private Long historyNo;
    /* RentManager와 매핑관계 맺지 않은 이유
    * 가정: 유저가 많은 도서 10000권 대여기록 있다
    *  1. 매핑 시 rentManager는 지연로딩이든 즉시로딩이든 10000권에 데이터를 불러온다.그러나 RentManager는 1권에 대한 처리(대여,반납,연장)을 하기에 적합하지 않다.
    *  1.1 만약 매핑 시 불러올 떄 한권만 불러올 수 있게 기술적으로 가능하다면 매핑관계 맺음 (ex.managerNo+"대여중" .. 대여중이라는 조건 추가 가능하다면?)
    */
    private Long managerNo;
    private Long userNo;
    private Long bookNo;
    private String rentDt;
    private String returnDt;
    private String haveToReturnDt;
    private boolean extensionFlg;
    private RentState rentState;


    // 해당 로직은 RentHistory 객체의 특정 필드 상태를 변경한다.
    // 현재, 루트 애그리거트(RentManager)에서 해당 클래스를 필드로 가지고 있다.
    // 만약 외부에서 루트 애그리거트를 가지고 get메소드로 해당 객체를 꺼내서 아래 메소드 수행하는 상황 방지
    // 루트애그리거트와 같은 패키지 위치에 두어 같은 패키지 내일때만 수행 가능하도록 접근제어 설정
    void returnBook(){
        writeReturnDt();

        if(isReturnOverdue()){
            log.info("해당 도서는 연체반납되었습니다.");
            changeRentState(RentState.OVERDUE_RETURN);
        }
        else{
            log.info("해당 도서는 정상반납되었습니다.");
            changeRentState(RentState.NORMAL_RETURN);
        }
    }
    private boolean isReturnOverdue(){
        String nowDate = DateUtil.getDate();
        return nowDate.compareTo(this.haveToReturnDt)>0 ;
    }
    private void changeRentState(RentState rentState){
        this.rentState = rentState;
    }
    private void writeReturnDt(){
        String nowDate = DateUtil.getDate();
        this.returnDt=nowDate;
    }

    void doExtend() {
        this.extensionFlg=true;
        this.haveToReturnDt+=6;
    }

    public static RentHistory by(RentHistoryEntity rentHistoryEntity){
        return RentHistory.builder()
                .historyNo(rentHistoryEntity.getHistoryNo())
                .managerNo(rentHistoryEntity.getManagerNo())
                .userNo(rentHistoryEntity.getUserNo())
                .bookNo(rentHistoryEntity.getBookNo())
                .rentDt(rentHistoryEntity.getRentDt())
                .returnDt(rentHistoryEntity.getReturnDt())
                .haveToReturnDt(rentHistoryEntity.getHaveToReturnDt())
                .extensionFlg(rentHistoryEntity.isExtensionFlg())
                .rentState(rentHistoryEntity.getRentState())
                .build()
                ;
    }
}
