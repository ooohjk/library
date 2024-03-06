package com.example.library.user.dao;

import com.example.library.user.entity.UserEntity;

public interface UserDAO {
    UserEntity getUser(Long userNo);
    String join(String userId, String userPwd, String userName, String tel, String email, String gender, Integer userFlg);
    String login(String userId, String userPwd);
}
