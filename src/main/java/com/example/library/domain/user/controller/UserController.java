package com.example.library.domain.user.controller;

import com.example.library.domain.user.dto.*;
import com.example.library.domain.user.service.UserService;
import com.example.library.domain.user.service.dto.UserRentStatusResDto;
import com.example.library.exception.ErrorCode;
import com.example.library.global.response.ApiResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
    public ApiResponseDto join(@Valid @RequestBody UserJoinReqDto userJoinReqDto) {
        userService.join(userJoinReqDto);
        return ApiResponseDto.createRes(ErrorCode.SUC);
    }

    @PostMapping("/login")
    public ApiResponseDto login(@Valid @RequestBody UserLoginReqDto userLoginReqDto) {
        UserLoginResDto userLoginResDto = userService.login(userLoginReqDto);
        return ApiResponseDto.createRes(ErrorCode.SUC, userLoginResDto);
    }

    @GetMapping("/get/userNo/{userNo}")
    public ApiResponseDto getUserByUserNo(@PathVariable("userNo") Long userNo) {
        UserSearchResDto userSearchResDto = userService.getUserByUserNo(userNo);
        return ApiResponseDto.createRes(ErrorCode.SUC, userSearchResDto);
    }

    @GetMapping("/get/userId/{userId}")
    public ApiResponseDto getUserByUserId(@PathVariable("userId") String userId) {
        UserSearchResDto userSearchResDto = userService.getUserByUserId(userId);
        return ApiResponseDto.createRes(ErrorCode.SUC, userSearchResDto);
    }

    @PutMapping("/update/{userNo}")
    public ApiResponseDto update(@PathVariable("userNo") Long userNo,@Valid @RequestBody UserUpdateDto userUpdateDto) {
        UserSearchResDto userSearchResDto = userService.update(userNo, userUpdateDto);
        return ApiResponseDto.createRes(ErrorCode.SUC, userSearchResDto);
    }

    @DeleteMapping("/delete/{userId}")
    public ApiResponseDto delete(@PathVariable("userId") String userId) {
        userService.delete(userId);
        return ApiResponseDto.createRes(ErrorCode.SUC);
    }

    @GetMapping("/getAll")
    public ApiResponseDto getAllUsers() {
        List<UserSearchResDto> userSearchResDtos = userService.getAllUsers();
        return ApiResponseDto.createRes(ErrorCode.SUC, userSearchResDtos);
    }

    @GetMapping("/myLibrary/hearts/{userNo}")
    public ApiResponseDto getMyHeartList(@PathVariable Long userNo){
        UserSelectHeartResDto userSelectHeartResDto = userService.getMyHeartList(userNo);
        return ApiResponseDto.createRes(ErrorCode.SUC, userSelectHeartResDto);
    }

    @PostMapping ("{userNo}/heart/{bookNo}")
    public ApiResponseDto heartBook(@PathVariable Long userNo,@PathVariable Long bookNo){
        userService.registerHeartBook(userNo,bookNo);
        return ApiResponseDto.createRes(ErrorCode.SUC);
    }

    @DeleteMapping("{userNo}/heart/{bookNo}")
    public ApiResponseDto removeHeartBook(@PathVariable Long userNo,@PathVariable Long bookNo) {
        userService.removeHeartBook(userNo,bookNo);
        return ApiResponseDto.createRes(ErrorCode.SUC);
    }

    @GetMapping("/myLibrary/rentStatus/{userNo}")
    public ApiResponseDto getRentStatus(@PathVariable Long userNo){
        List<UserRentStatusResDto> userRentStatusResDtos = userService.getCurrentRentStatus(userNo);
        return ApiResponseDto.createRes(ErrorCode.SUC,userRentStatusResDtos);
    }
}
