package com.example.library.domain.user.service;

import com.example.library.domain.user.dto.*;
import com.example.library.domain.user.enums.UserGrade;

import java.util.List;

public interface UserService {
    void join(UserJoinReqDto userJoinReqDto);
    UserLoginResDto login(UserLoginReqDto userLoginReqDto);
    UserSearchResDto getUserByUserNo(Long userNo);
    UserSearchResDto getUserByUserId(String userId);
    UserGrade getUserGrade(String userId);
    UserSearchResDto update(Long userNo, UserUpdateDto userUpdateDto);
    void delete(String userId);
    List<UserSearchResDto> getAllUsers();

    //찜 관련
    UserSelectHeartResDto getMyHeartList(Long userNo);
    void registerHeartBook(UserHeartBookReqDto userHeartBookReqDto);
    void removeHeartBook(UserRemoveHeartBookReqDto userRemoveHeartBookReqDto);
}
