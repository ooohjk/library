package com.example.library.global.security.oauth2.userInfo.OAuthUserInfo;

import lombok.AllArgsConstructor;

import java.util.Map;

//인터페이스가 아닌 추상클래스인 이유 : attributes 필드를 가지고 있어야 하기 떄문
@AllArgsConstructor
public abstract class OAuthUserInfo {
    protected String usernameAttributeName;
    protected Map<String,Object> attributes;


    public abstract String getProviderId();
    public abstract String getEmail();
    public abstract String getName();
}
