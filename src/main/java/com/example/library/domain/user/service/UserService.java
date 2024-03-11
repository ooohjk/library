package com.example.library.domain.user.service;

import com.example.library.domain.user.dto.UserJoinReqDto;
import com.example.library.domain.user.dto.UserLoginReqDto;
import com.example.library.domain.user.dto.UserLoginResDto;
import com.example.library.domain.user.dto.UserSearchResDto;

public interface UserService {
    void join(UserJoinReqDto userJoinReqDto);
    UserLoginResDto login(UserLoginReqDto userLoginReqDto);
    UserSearchResDto getUserByUserNo(Long userNo);
    UserSearchResDto getUserByUserId(String userId);
}
