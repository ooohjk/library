package com.example.library.domain.rent.domain;

import com.example.library.domain.rent.infrastructure.RentManagerEntity;
import com.example.library.exception.ErrorCode;
import com.example.library.exception.exceptions.ExceedMaximumRentNumberException;
import com.example.library.exception.exceptions.OverdueUserException;
import com.example.library.global.utils.DateUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
public class RentManager {
    private final int initialRentNumber=0;
    private final int maximumRentNumber=2;

    private Long managerNo;
    private Long userNo;
    private int currentRentNumber;
    private boolean overdue;
    private List<RentHistory> rentHistoryList=new ArrayList<>(); //이게 필요한가?

    public void rentBook(Long bookNo){
        plusCurrentRentNumber();
        createRentHistory(bookNo);
    }

    private void plusCurrentRentNumber(){
        currentRentNumber=currentRentNumber+1;

        log.info(String.format("대여 후 현재 대여 중인 권수: [%s]",String.valueOf(currentRentNumber)));
    }

    private void minusCurrentRentNumber(){
        currentRentNumber=currentRentNumber-1;

        log.info(String.format("대여 후 현재 대여 중인 권수: [%s]",String.valueOf(currentRentNumber)));
    }

    private void createRentHistory(Long bookNo){
        log.info("대여 히스토리 생성 완료");

        rentHistoryList.add(
                RentHistory.createRentHistory()
                        .managerNo(managerNo)
                        .bookNo(bookNo)
                        .build()
        );
    }

    public void checkRentAvailable(){
        log.info("[checkRentAvailable] 유저 대여 가능 여부 확인");

        isOverdueUser();
        checkRentNumber();

        log.info("[checkRentAvailable] 유저 대여 가능");
    }


    private void isOverdueUser(){
        if(overdue){
            log.error(String.format("해당 유저번호(%s) 사용자는 연체 등록된 사용자입니다.",managerNo.toString()));
            throw new OverdueUserException(ErrorCode.OVERDUE_USER);
        }

        log.info(String.format("해당 유저번호(%s) 사용자는 연체 등록되지 않은 사용자입니다.",managerNo.toString()));
    }

    private void checkRentNumber(){
        if(currentRentNumber == maximumRentNumber){
            log.error(String.format("대여 최대 가능 권수를 초과하였습니다. 현재 대여권수[%s] / 최대 가능 권수[%s]",String.valueOf(currentRentNumber),String.valueOf(maximumRentNumber)));

            throw new ExceedMaximumRentNumberException(ErrorCode.EXCEED_MAXIMUN_RENT_NUMBER);
        }

        log.info(String.format("해당 유저는 최대 대여 가능 권수 내입니다. 현재 대여권수[%s] / 최대 가능 권수[%s]",String.valueOf(currentRentNumber),String.valueOf(maximumRentNumber)));
    }


    public void returnBook(RentHistory rentHistory){
        rentHistory.returnBook();
        minusCurrentRentNumber();
    }

    
    //아래 두개 합치는 생각 해보자
    public RentManagerEntity toNewEntity(){
        return new RentManagerEntity(userNo,initialRentNumber);
    }
    public RentManagerEntity toOldEntity(){
        return new RentManagerEntity(managerNo,userNo,currentRentNumber,overdue);
    }

    public static RentManager from(RentManagerEntity rentManagerEntity){
        log.info(String.format("rent Manager 조회 성공 - managerNo[%s] 연체여부[%s] 현재대여권수[%s]",rentManagerEntity.getManagerNo().toString(),String.valueOf(rentManagerEntity.isOverdueFlg()),rentManagerEntity.getCurrentRentNumber()));

        return RentManager.getRentManager()
                .managerNo(rentManagerEntity.getManagerNo())
                .currentRentNumber(rentManagerEntity.getCurrentRentNumber())
                .userNo(rentManagerEntity.getUserNo())
                .overdue(rentManagerEntity.isOverdueFlg())
                .build()
                ;
    }

    /* @Builder */
    @Builder(builderMethodName = "getRentManager",builderClassName = "getRentManager")
    public RentManager(Long managerNo, Long userNo, int currentRentNumber, boolean overdue) {
        this.managerNo = managerNo;
        this.userNo = userNo;
        this.currentRentNumber = currentRentNumber;
        this.overdue = overdue;
    }

    @Builder(builderMethodName ="createRentManager",builderClassName ="createRentManager" )
    public RentManager(Long userNo){
        this.userNo=userNo;
    }
}
