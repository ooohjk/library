package com.example.library.test;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class ErrorCodeView {
    private Map<String, String> errorCodes;
}