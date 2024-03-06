package com.example.library.user.service.Impl;

import com.example.library.user.dao.UserDAO;
import com.example.library.user.entity.UserEntity;
import com.example.library.user.dto.UserDto;
import com.example.library.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public String join(String userId, String userPwd, String userName, String tel, String email, String gender, Integer userFlg) {
        return userDAO.join(userId, userPwd, userName, tel, email, gender, userFlg);
    }

    public String login(String userId, String userPwd) {
        return userDAO.login(userId, userPwd);
    }

    public UserDto getUser(Long userNo) {
        UserEntity userEntity =  userDAO.getUser(userNo);

        UserDto userDto = new UserDto(userEntity.getUserId(), userEntity.getUserPwd(), userEntity.getUserName(), userEntity.getTel(), userEntity.getUserEmail(), userEntity.getGender(), userEntity.getUseFlg());
        return userDto;
    }
}
