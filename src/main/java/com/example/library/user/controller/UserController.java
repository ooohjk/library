package com.example.library.user.controller;

import com.example.library.user.dto.UserJoinReqDto;
import com.example.library.user.dto.UserLoginReqDto;
import com.example.library.user.dto.UserLoginResDto;
import com.example.library.user.dto.UserSearchResDto;
import com.example.library.user.service.UserService;
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
    public ResponseEntity<String> join(@RequestBody UserJoinReqDto userJoinReqDto) {
        userService.join(userJoinReqDto);
        return ResponseEntity.ok().body("Success Join!!");
    }

    @PostMapping("/login")
    public UserLoginResDto login(@RequestBody UserLoginReqDto userLoginReqDto) {
        UserLoginResDto userLoginResDto = userService.login(userLoginReqDto);
        return userLoginResDto;
    }

    @GetMapping("/get/userNo")
    public UserSearchResDto getUserByUserNo(Long userNo) {
        return userService.getUserByUserNo(userNo);
    }

    @GetMapping("/get/userId")
    public UserSearchResDto getUserByUserId(String userId) {
        return userService.getUserByUserId(userId);
    }
}
