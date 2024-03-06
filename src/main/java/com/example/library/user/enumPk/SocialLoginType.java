package com.example.library.user.enumPk;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

public enum SocialLoginType {
    GOOGLE,
    NAVER,
    KAKAO,
    NONE;

    public static SocialLoginType getSocialType(String type){
        if(type == null){
            return SocialLoginType.NONE;
        }

        return Arrays.stream(SocialLoginType.values())
                .filter(socialLoginType->socialLoginType.name().equals(type.toUpperCase()))
                .findFirst()
                .orElseThrow(()->new RuntimeException("존재하지 않는 소셜로그인 타입입니다."))
        ;
    }
}
