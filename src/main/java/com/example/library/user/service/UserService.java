package com.example.library.user.service;

import com.example.library.user.dto.UserDto;
import com.example.library.user.dto.UserLoginResDto;

public interface UserService {
    String join(String userId, String userPwd, String userName, String tel, String email, String gender, Integer userFlg);
    UserLoginResDto login(String userId, String userPwd);
    UserDto getUserByUserNo(Long userNo);
    UserDto getUserByUserId(String userId);
}
