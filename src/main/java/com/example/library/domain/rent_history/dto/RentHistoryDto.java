package com.example.library.domain.rent_history.dto;

import com.example.library.domain.rent_history.entity.RentHistoryEntity;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Data
public class RentHistoryDto {

    @NotNull
    private Long rentNo;

    @NotNull
    private Long userNo;

    @NotNull
    private Long bookCode;

    @NotNull
    private String bookName;

    @NotNull
    private String rentDt;

    @NotNull
    private String prospectDt;

    private String returnDt;

    @NotNull
    private Boolean extension;

    @NotNull
    private Integer rentState;

    public RentHistoryDto(RentHistoryEntity rentHistory) {
        this.rentNo = rentHistory.getRentNo();
        this.userNo = rentHistory.getUser().getUserNo();
        this.bookCode = rentHistory.getBook().getBookCode();
        this.bookName = rentHistory.getBook().getBookName();
        this.rentDt = rentHistory.getRentDt();
        this.prospectDt = rentHistory.getProspectDt();
        this.returnDt = rentHistory.getReturnDt();
        this.extension = rentHistory.getExtension();
        this.rentState = rentHistory.getRentState();
    }

    public static RentHistoryDto info(RentHistoryEntity rentHistory) {
        return new RentHistoryDto(rentHistory);
    }
}
