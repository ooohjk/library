package com.example.library.domain.rent.application.dto;

import com.example.library.domain.rent.domain.RentHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
@AllArgsConstructor
public class UserRentStatusResDto {
    private Long bookNo;
    private String bookName;
    private String rentDt;
    private String haveToReturnDt;
    private boolean extensionFlg;

    public static UserRentStatusResDto from(RentHistory rentHistory){
        return UserRentStatusResDto.builder()
                .bookNo(rentHistory.getBookNo())
                .bookName("OpenFeign으로 가져오기")
                .rentDt(rentHistory.getRentDt())
                .haveToReturnDt(rentHistory.getHaveToReturnDt())
                .extensionFlg(rentHistory.isExtensionFlg())
                .build()
        ;
    }
}
