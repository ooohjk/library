package com.example.library.user.service;

import com.example.library.user.dto.UserDto;

public interface UserService {
//    String login(String userId, String userPwd);
    UserDto getAllUser(String userId);
}
