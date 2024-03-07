package com.example.library.user.dto;

import com.example.library.user.entity.UserEntity;
import com.example.library.utils.JwtUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserLoginResDtoTest {

    @Test
    public void 조회된유저엔티티to로그인응답dto_변환테스트(){
        String id= "id";
        String email = "sda@naver.com";
        String name = "테스트";
        String pwd = "pwd";
        String providerId = "213214142";

        //given
        String accessToken = JwtUtil.createJwt("테스트");
        UserEntity user = UserEntity.createOAuth2User()
                .userId(id)
                .userEmail(email)
                .userName(name)
                .userPwd(pwd)
                .providerId(providerId)
                .build();

        //when
        UserLoginResDto userLoginResDto = UserLoginResDto.from(user,accessToken);

        //then
        assertAll(
                ()->userLoginResDto.getUserId().equals(id),
                ()->userLoginResDto.getUserEmail().equals(email),
                ()->userLoginResDto.getProviderId().equals(providerId)
                );
    }
}