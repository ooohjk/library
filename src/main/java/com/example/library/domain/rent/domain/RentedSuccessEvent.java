package com.example.library.domain.rent.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RentedSuccessEvent {
    private Long bookNo;
}
