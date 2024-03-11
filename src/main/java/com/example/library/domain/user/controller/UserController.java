package com.example.library.domain.user.controller;

import com.example.library.domain.user.dto.UserJoinReqDto;
import com.example.library.exception.ErrorCode;
import com.example.library.global.response.ApiResponseDto;
import com.example.library.domain.user.dto.UserLoginReqDto;
import com.example.library.domain.user.dto.UserLoginResDto;
import com.example.library.domain.user.dto.UserSearchResDto;
import com.example.library.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "user")
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public ApiResponseDto join(@RequestBody UserJoinReqDto userJoinReqDto) {
        userService.join(userJoinReqDto);
        return ApiResponseDto.createRes(ErrorCode.SUC);
    }

    @PostMapping("/login")
    public ApiResponseDto login(@RequestBody UserLoginReqDto userLoginReqDto) {
        UserLoginResDto userLoginResDto = userService.login(userLoginReqDto);
        return ApiResponseDto.createRes(ErrorCode.SUC,userLoginResDto);
    }

    @GetMapping("/get/userNo/{userNo}")
    public ApiResponseDto getUserByUserNo(@PathVariable("userNo") Long userNo) {
        UserSearchResDto userSearchResDto = userService.getUserByUserNo(userNo);
        return ApiResponseDto.createRes(ErrorCode.SUC,userSearchResDto);
    }

    @GetMapping("/get/userId/{userId}")
    public UserSearchResDto getUserByUserId(@PathVariable("userId") String userId) {
        return userService.getUserByUserId(userId);
    }

    @GetMapping("/test/{name}")
    public ResponseEntity<TestResDto> hi(@PathVariable("name") String name){
        return ResponseEntity.ok()
                .body(new TestResDto(name))
                ;
    }
}
