package com.example.library.user.service.Impl;

import com.example.library.user.dao.UserDAO;
import com.example.library.user.entity.UserEntity;
import com.example.library.user.dto.UserDto;
import com.example.library.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDto getAllUser(Long userNo) {
        UserEntity userEntity =  userDAO.getAllUser(userNo);

        if(userEntity == null) {
            userEntity.setUserNo((long)1);
            userEntity.setUserId("admin");
            userEntity.setUserPwd("1234");
            userEntity.setUserName("오재경");
            userEntity.setTel("010-0000-0000");
            userEntity.setUserEmail("xxx@naver.com");
            userEntity.setGender("M");
            userEntity.setUseFlg(0);
        }

        UserDto userDto = new UserDto(userEntity.getUserNo(), userEntity.getUserId(), userEntity.getUserPwd(), userEntity.getUserName(), userEntity.getTel(), userEntity.getUserEmail(), userEntity.getGender(), userEntity.getUseFlg());
        return userDto;
    }
}
