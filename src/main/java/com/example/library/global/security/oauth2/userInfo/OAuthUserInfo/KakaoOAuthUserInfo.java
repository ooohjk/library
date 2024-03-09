package com.example.library.global.security.oauth2.userInfo.OAuthUserInfo;

import com.example.library.global.security.oauth2.userInfo.OAuthUserInfo.interfaces.OAuthUserInfo;

import java.util.Map;

public class KakaoOAuthUserInfo extends OAuthUserInfo {
    private final String KAKAO_ACCOUNT= "kakao_account";

    public KakaoOAuthUserInfo(String usernameAttributeName, Map<String, Object> attributes) {
        super(usernameAttributeName, attributes);
    }

    @Override
    public String getProviderId() {
        Long providerId = (Long)attributes.get(usernameAttributeName);
        return String.valueOf(providerId);
    }

    @Override
    public String getEmail() {
        Map<String,Object> kakaoAccountMap = (Map<String, Object>) attributes.get(KAKAO_ACCOUNT);
        return (String)kakaoAccountMap.get("email");
    }

    @Override
    public String getName() {
        return null;
    }
}
