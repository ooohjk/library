package com.example.library.domain.rent.manager.dto;

import com.example.library.domain.rent.manager.entity.RentManagerEntity;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentManagerDto {

    @NotNull
    private Long managerNo;

    @NotNull
    private Long userNo;

    @NotNull
    private Boolean overdue;

    @NotNull
    private Integer rentNumber;

    private String lastReturnDt;

    private RentManagerDto(RentManagerEntity rentManager) {
        this.managerNo = rentManager.getManagerNo();
        this.userNo = rentManager.getUser().getUserNo();
        this.overdue = rentManager.getOverdue();
        this.rentNumber = rentManager.getRentNumber();
        this.lastReturnDt = rentManager.getLastReturnDt();
    }

    public static RentManagerDto info(RentManagerEntity rentManager) {
        return new RentManagerDto(rentManager);
    }
}
