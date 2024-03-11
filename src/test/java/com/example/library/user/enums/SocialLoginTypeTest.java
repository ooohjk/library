package com.example.library.user.enums;

import com.example.library.domain.user.enums.SocialLoginType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SocialLoginTypeTest {

    @Test
    public void 문자형의소셜로그인을Eum클래스로변환테스트(){
        System.out.println(SocialLoginType.GOOGLE.name().toLowerCase());

        String lowerType = "google";
        String UpperType = "GOOGLE";
        SocialLoginType SocialLoginTypeFromLower = SocialLoginType.getSocialType(lowerType);
        SocialLoginType SocialLoginTypeFromUpper = SocialLoginType.getSocialType(UpperType);

        Assertions.assertThat(SocialLoginTypeFromLower).isEqualTo(SocialLoginType.GOOGLE);
        Assertions.assertThat(SocialLoginTypeFromUpper).isEqualTo(SocialLoginType.GOOGLE);
    }
}