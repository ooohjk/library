package com.example.library.user.service;

import com.example.library.user.dto.UserJoinReqDto;
import com.example.library.user.dto.UserLoginReqDto;
import com.example.library.user.dto.UserLoginResDto;
import com.example.library.user.dto.UserSearchResDto;

public interface UserService {
    void join(UserJoinReqDto userJoinReqDto);
    UserLoginResDto login(UserLoginReqDto userLoginReqDto);
    UserSearchResDto getUserByUserNo(Long userNo);
    UserSearchResDto getUserByUserId(String userId);
}
