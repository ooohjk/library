package com.example.library.domain.rent.domain;

import com.example.library.domain.rent.infrastructure.entity.RentManagerEntity;
import com.example.library.exception.ErrorCode;
import com.example.library.exception.exceptions.ExceedMaximumRentNumberException;
import com.example.library.exception.exceptions.ExtendNumberExceedException;
import com.example.library.exception.exceptions.OverdueUserException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

//도메인 비즈니스와 영속화 로직을 완전히 분리시킨다.

@Slf4j
@Getter
@Builder
@AllArgsConstructor
public class RentManager {

    @Builder(builderMethodName = "createRentManager",builderClassName = "createRentManager")
    public RentManager(Long userNo) {
        this.userNo=userNo;
        this.currentRentNumber=initialRentNumber;
        this.overdue=false;
    }

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

    public void returnBook(){
        rentHistory.returnBook();
        minusCurrentRentNumber();
    }

    public void extendBook(){
        checkExtendAvailable();
        rentHistory.doExtend();
    }

    private void doRent(Long bookNo){
        plusCurrentRentNumber();
        createRentHistory(bookNo);
    }

    private void plusCurrentRentNumber(){
        currentRentNumber=currentRentNumber+1;
    }

    private void minusCurrentRentNumber(){
        currentRentNumber=currentRentNumber-1;
    }

    private void createRentHistory(Long bookNo){
        this.rentHistory= RentHistory.createRentHistory()
                        .managerNo(managerNo)
                        .userNo(userNo)
                        .bookNo(bookNo)
                        .build()
        ;
    }

    public void setRentHistory(RentHistory rentHistory) {
        this.rentHistory = rentHistory;
    }

    private void checkRentAvailable(){
        log.info("[checkRentAvailable] 유저 대여 가능 여부 확인");

        checkOverdueUser();
        checkRentNumber();

        log.info("[checkRentAvailable] 유저 대여 가능");
    }

    private void checkOverdueUser(){
        if(overdue){
            log.error(String.format("해당 유저번호(%s) 사용자는 연체 등록된 사용자입니다.",managerNo.toString()));
            throw new OverdueUserException(ErrorCode.OVERDUE_USER);
        }

    }

    private void checkRentNumber(){
        if(currentRentNumber == maximumRentNumber){
            log.error(String.format("대여 최대 가능 권수를 초과하였습니다. 현재 대여권수[%s] / 최대 가능 권수[%s]",String.valueOf(currentRentNumber),String.valueOf(maximumRentNumber)));

            throw new ExceedMaximumRentNumberException(ErrorCode.EXCEED_MAXIMUN_RENT_NUMBER);
        }

    }

    private void checkExtendAvailable(){
        log.info("[checkExtendAvailable] 유저 연장 가능 여부 확인");

        checkOverdueUser();
        checkExtendBookBefore();
        //추가 검증 로직 추가 가능
        // checkBookReserved(); 예약여부 확인

    }

    private void checkExtendBookBefore(){
        if(rentHistory.isExtensionFlg()){
            log.error("도서 연장 횟수 초과하였습니다.");
            throw new ExtendNumberExceedException(ErrorCode.BOOK_EXTEND_NUMBER_EXCEED);
        }
    }

    public static RentManager by(RentManagerEntity rentManagerEntity){
        return RentManager.builder()
                .managerNo(rentManagerEntity.getManagerNo())
                .currentRentNumber(rentManagerEntity.getCurrentRentNumber())
                .userNo(rentManagerEntity.getUserNo())
                .overdue(rentManagerEntity.isOverdueFlg())
                .build()
                ;
    }
}
