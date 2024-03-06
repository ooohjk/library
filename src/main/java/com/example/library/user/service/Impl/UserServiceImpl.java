package com.example.library.user.service.Impl;

import com.example.library.exception.AppException;
import com.example.library.exception.ErrorCode;
import com.example.library.user.entity.UserEntity;
import com.example.library.user.dto.UserDto;
import com.example.library.user.repository.UserRepository;
import com.example.library.user.service.UserService;
import com.example.library.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    @Value("${jwt.secret}")
    private String secret;
    private final Long expiredMs = 1000 * 60 * 60L;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public String join(String userId, String userPwd, String userName, String tel, String email, String gender, Integer userFlg) {
        userRepository.findByUserId(userId).ifPresent(user -> {
            throw new AppException(ErrorCode.USERID_DUPLICATED, userId + " 는 이미 존재합니다.");
        });
        UserEntity userEntity =
                UserEntity.builder()
                        .userId(userId)
                        .userPwd(encoder.encode(userPwd))
                        .userName(userName)
                        .tel(tel)
                        .userEmail(email)
                        .gender(gender)
                        .useFlg(userFlg)
                        .build();
        userRepository.save(userEntity);
        return "Success!!";
    }

    public String login(String userId, String userPwd) {
        UserEntity selectedUser = userRepository.findByUserId(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USERID_NOT_FOUND, userId + "이 없습니다."));

        if(!encoder.matches(userPwd, selectedUser.getUserPwd())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD, "패스워드를 잘못 입력 하였습니다.");
        }

        String token = JwtUtil.createJwt(selectedUser.getUserId(), secret, expiredMs);

        return token;
    }

    @Override
    public UserDto getUserByUserNo(Long userNo) {
        UserEntity userEntity = userRepository.findByUserNo(userNo).get();

        UserDto userDto = new UserDto(userEntity.getUserId(), userEntity.getUserPwd(), userEntity.getUserName(), userEntity.getTel(), userEntity.getUserEmail(), userEntity.getGender(), userEntity.getUseFlg());
        return userDto;
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId).get();

        UserDto userDto = new UserDto(userEntity.getUserId(), userEntity.getUserPwd(), userEntity.getUserName(), userEntity.getTel(), userEntity.getUserEmail(), userEntity.getGender(), userEntity.getUseFlg());
        return userDto;
    }
}
