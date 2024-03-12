package com.example.library.test;

import com.example.library.exception.ErrorCode;
import com.example.library.test.dto.ErrorCodeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/test")
public class CommonDocController {

    @GetMapping("/error-code/map")
    public ResponseEntity getErrorCode() {
        Map<String, String> map = new HashMap<>();
        for (ErrorCode errorCode : ErrorCode.values()) {
            map.put(errorCode.getCode(), errorCode.getMsg());
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/error-code/dto")
    public ResponseEntity<ErrorCodeDto> getErrorCodes() {

        Map<String, String> errorCodes = Arrays.stream(ErrorCode.values())
                .collect(Collectors.toMap(ErrorCode::getCode, ErrorCode::getMsg));

        return new ResponseEntity<>(new ErrorCodeDto(errorCodes), HttpStatus.OK);
    }


    @GetMapping("/commonFormatInRespnse")
    public ResponseEntity<Map> getCommonFormat(){

        Map<String,String> map = new HashMap<>();
        //apiResponse와 맞춰주어야 한다.
        map.put("code","0");
        map.put("msg","OK");
        map.put("data","데이터");

        return new ResponseEntity<>(map, HttpStatus.OK);
    }


}
