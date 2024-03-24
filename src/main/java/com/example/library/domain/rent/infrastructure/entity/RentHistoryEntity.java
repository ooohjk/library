package com.example.library.domain.rent.infrastructure.entity;

import com.example.library.domain.rent.RentState;
import com.example.library.domain.rent.infrastructure.entity.converter.RentStateConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rent_history")
public class RentHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyNo;

    private Long managerNo;

    private Long userNo;

    private Long bookNo;

    private String rentDt;

    private String returnDt;

    private String haveToReturnDt;

    private boolean extensionFlg;

    @Convert(converter = RentStateConverter.class)
    private RentState rentState;
}
