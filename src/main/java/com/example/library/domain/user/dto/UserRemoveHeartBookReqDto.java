package com.example.library.domain.user.dto;

import lombok.Getter;

@Getter
public class UserRemoveHeartBookReqDto {
    private Long userNo;
    private Long heartNo;
}
