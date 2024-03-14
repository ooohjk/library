package com.example.library.domain.user.controller;

import com.example.library.domain.user.dto.*;
import com.example.library.domain.user.service.UserService;
import com.example.library.exception.ErrorCode;
import com.example.library.global.response.ApiResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


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

    @PutMapping("/update/{userId}")
    public UserSearchResDto update(@PathVariable("userId") String userId, @RequestBody UserUpdateDto userUpdateDto) {
        return userService.update(userId, userUpdateDto);
    }

    @DeleteMapping("/delete/{userId}")
    public void delete(@PathVariable("userId") String userId) {
        userService.delete(userId);
    }

    @GetMapping("/getAll")
    public List<UserSearchResDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/hearts")
    public ApiResponseDto getMyHeartList(@RequestBody Map<String,Long> userNoMap){
        UserSelectHeartResDto userSelectHeartResDto =userService.getMyHeartList(userNoMap.get("userNo"));
        return ApiResponseDto.createRes(ErrorCode.SUC, userSelectHeartResDto);
    }

    @PostMapping ("/heart/reg")
    public ApiResponseDto heartBook(@RequestBody UserHeartBookReqDto userHeartBookReqDto){
        userService.registerHeartBook(userHeartBookReqDto);
        return ApiResponseDto.createRes(ErrorCode.SUC);
    }

    @DeleteMapping("/heart/remove")
    public ApiResponseDto removeHeartBook(@RequestBody UserRemoveHeartBookReqDto userRemoveHeartBookReqDto) {
        userService.removeHeartBook(userRemoveHeartBookReqDto);
        return ApiResponseDto.createRes(ErrorCode.SUC);
    }
}
