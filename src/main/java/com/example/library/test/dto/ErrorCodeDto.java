package com.example.library.test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class ErrorCodeDto {
    private Map<String, String> errorCodes;
}