package com.example.library.global.security.oauth2.userInfo.OAuthUserInfo.userInfos;

import com.example.library.global.security.oauth2.userInfo.OAuthUserInfo.OAuthUserInfo;

import java.util.Map;

public class NaverOAuthUserInfo extends OAuthUserInfo {

    public NaverOAuthUserInfo(String usernameAttributeName, Map<String, Object> attributes) {
        super(usernameAttributeName, attributes);
    }

    public String getProviderId() {
        return (String)getResponseMap().get("id");
    }

    public String getEmail() {
        return (String)getResponseMap().get("email");
    }

    public String getName(){
        return (String)getResponseMap().get("name");
    }

    public String getGender(){
        return (String)getResponseMap().get("gender");
    }

    public String getTel(){
        return (String)getResponseMap().get("mobile");
    }

    public String getBirthDt(){
        String brithYear = (String)getResponseMap().get("birthyear");
        String brithDay = (String)getResponseMap().get("birthday");
        return brithYear+"/"+brithDay;
    }


    private Map<String,Object> getResponseMap(){
        return (Map<String, Object>) attributes.get(usernameAttributeName);
    }

}
