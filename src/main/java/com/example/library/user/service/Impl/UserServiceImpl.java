package com.example.library.user.service.Impl;

import com.example.library.user.dao.UserDAO;
import com.example.library.user.entity.UserEntity;
import com.example.library.user.dto.UserDto;
import com.example.library.user.service.UserService;
//import com.example.library.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
//    @Value("${jwt.secret}")
//    private String secretKey;
//
//    private final Long expiredMs = 1000 * 60 * 60l;

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDto getAllUser(String userId) {
        UserEntity userEntity =  userDAO.getAllUser(userId);
        UserDto userDto = new UserDto(userEntity.getUserNo(), userEntity.getUserId(), userEntity.getUserPwd(), userEntity.getCreatedDate(), userEntity.getUserName(), userEntity.getTel(), userEntity.getUserEmail(), userEntity.getGender(), userEntity.getUseFlg());
        return userDto;
    }

//    @Override
//    public String login(String userId, String userPwd) {
//        return "login";
//    }

//    public String login(String userName, String password) {
//        return JwtUtil.createJwt(userName, secretKey, expiredMs);
//    }
}
