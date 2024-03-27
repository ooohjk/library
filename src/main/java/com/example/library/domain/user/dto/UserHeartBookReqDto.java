package com.example.library.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserHeartBookReqDto {
    private Long userNo;
    private Long bookCode;
}
