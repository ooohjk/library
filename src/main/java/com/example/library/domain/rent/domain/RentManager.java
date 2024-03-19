package com.example.library.domain.rent.domain;

import com.example.library.domain.rent.infrastructure.RentManagerEntity;
import com.example.library.exception.ErrorCode;
import com.example.library.exception.exceptions.ExceedMaximumRentNumberException;
import com.example.library.exception.exceptions.ExtendNumberExceedException;
import com.example.library.exception.exceptions.OverdueUserException;
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
    private Long userNo; //다른 애그리거트이므로 id참조
    private int currentRentNumber;
    private boolean overdue;
    //이게 필요한가? if 필요하다면 , getMethod로 외부에서 접근가능할것으로도 보인다. 이거 어케 막을거냐?
    //A.해당 클래스와 RentHistory클래스를 같은 패키지 내로 두고 접근제어자 디폴트로 막았다.
    private RentHistory rentHistory;
    public void rentBook(Long bookNo){
        checkRentAvailable();
        doRent(bookNo);
    }
    public void returnBook(RentHistory rentHistory){
        rentHistory.returnBook();
        minusCurrentRentNumber();
    }
    public void extendBook(RentHistory rentHistory){
        checkExtendAvailable(rentHistory);
        rentHistory.doExtend();
    }
    private void doRent(Long bookNo){
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

        this.rentHistory= RentHistory.createRentHistory()
                        .managerNo(managerNo)
                        .bookNo(bookNo)
                        .build()
        ;
    }
    private void checkRentAvailable(){
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
    private void checkExtendAvailable(RentHistory rentHistory){
        log.info("[checkExtendAvailable] 유저 연장 가능 여부 확인");

        isOverdueUser();
        checkExtendBookBefore(rentHistory);
        //추가 검증 로직 추가 가능
        // checkBookReserved(); 예약여부 확인

        log.info("[checkExtendAvailable] 유저 연장 확인");
    }
    private void checkExtendBookBefore(RentHistory rentHistory){
        if(rentHistory.isExtensionFlg()){
            log.error("도서 연장 횟수 초과하였습니다.");
            throw new ExtendNumberExceedException(ErrorCode.BOOK_EXTEND_NUMBER_EXCEED);
        }
    }

    //아래 두개 합치는 생각 해보자
    public RentManagerEntity toNewEntity(){
        return new RentManagerEntity(userNo,initialRentNumber);
    }
    public RentManagerEntity toEntity(){
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
