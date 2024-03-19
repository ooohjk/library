package com.example.library.domain.rent.domain;

import com.example.library.domain.rent.RentState;
import com.example.library.domain.rent.infrastructure.RentHistoryEntity;
import com.example.library.global.utils.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@AllArgsConstructor
@Builder(builderMethodName = "getRentHistory", builderClassName = "getRentHistory")
public class RentHistory {

    private Long historyNo;
    private Long managerNo;
//    private Long userNo;
    private Long bookNo;
    private String rentDt;
    private String returnDt;
    private String haveToReturnDt;
    private boolean extensionFlg;
    private RentState rentState;

    @Builder(builderMethodName = "createRentHistory")
    public RentHistory(Long managerNo, Long bookNo) {
        this.managerNo = managerNo;
        this.bookNo = bookNo;
    }

    public void returnBook(){
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
        return nowDate.compareTo(this.getHaveToReturnDt())>0 ;
    }

    private void changeRentState(RentState rentState){
        this.rentState = rentState;
    }

    private void writeReturnDt(){
        String nowDate = DateUtil.getDate();
        log.info(String.format("반납일자[%s]",nowDate));

        this.returnDt=nowDate;
    }


    public RentHistoryEntity toEntity() {
        return new RentHistoryEntity(managerNo,bookNo);
    }
    public RentHistoryEntity toOldEntity() {
        return new RentHistoryEntity(historyNo,managerNo,bookNo,rentDt,returnDt,haveToReturnDt,extensionFlg,rentState);
    }

    public static RentHistory from(RentHistoryEntity rentHistoryEntity){
        return RentHistory.getRentHistory()
                .historyNo(rentHistoryEntity.getHistoryNo())
                .managerNo(rentHistoryEntity.getManagerNo())
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
