package com.example.library.domain.user.dto;

import com.example.library.domain.user.service.dto.HeartResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class UserSelectHeartResDto {
    private Long userNo;

    List<HeartResponseDto> heartList;
}
