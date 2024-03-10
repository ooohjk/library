package com.example.library.test;

import com.example.library.exception.ErrorCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ErrorCodeController {

    @GetMapping("/error-code")
    public Map<String, String> findEnums() {
        Map<String, String> map = new HashMap<>();
        for (ErrorCode errorCode : ErrorCode.values()) {
            map.put(errorCode.getCode(), errorCode.getMsg());
        }
        return map;
    }
}
