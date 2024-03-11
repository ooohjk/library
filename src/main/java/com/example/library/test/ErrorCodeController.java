package com.example.library.test;

import com.example.library.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ErrorCodeController {

    @GetMapping("/error-code/map")
    public ResponseEntity getErrorCode() {
        Map<String, String> map = new HashMap<>();
        for (ErrorCode errorCode : ErrorCode.values()) {
            map.put(errorCode.getCode(), errorCode.getMsg());
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/error-code/dto")
    public ResponseEntity<ErrorCodeView> getErrorCodes() {

        Map<String, String> errorCodes = Arrays.stream(ErrorCode.values())
                .collect(Collectors.toMap(ErrorCode::getCode, ErrorCode::getMsg));

        return new ResponseEntity<>(new ErrorCodeView(errorCodes), HttpStatus.OK);
    }

}
