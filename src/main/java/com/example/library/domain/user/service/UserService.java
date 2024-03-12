package com.example.library.domain.user.service;

import com.example.library.domain.user.dto.*;

public interface UserService {
    void join(UserJoinReqDto userJoinReqDto);
    UserLoginResDto login(UserLoginReqDto userLoginReqDto);
    UserSearchResDto getUserByUserNo(Long userNo);
    UserSearchResDto getUserByUserId(String userId);
    UserSearchResDto update(String userId, UserUpdateDto userUpdateDto);
    void delete(String userId);
}
