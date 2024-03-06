package com.example.library.user.service;

import com.example.library.user.dto.UserDto;

public interface UserService {
    String join(String userId, String userPwd, String userName, String tel, String email, String gender, Integer userFlg);
    String login(String userId, String userPwd);
    UserDto getUserByUserNo(Long userNo);
    UserDto getUserByUserId(String userId);
}
