package com.example.library.global.security.oauth2.userInfo.OAuthUserInfo.userInfos;

import com.example.library.global.security.oauth2.userInfo.OAuthUserInfo.OAuthUserInfo;

import java.util.Map;

public class GoogleOAuthUserInfo extends OAuthUserInfo {

    public GoogleOAuthUserInfo(String usernameAttributeName, Map<String, Object> attributes) {
        super(usernameAttributeName, attributes);
    }

    public String getProviderId() {
        return (String)attributes.get(usernameAttributeName);
    }

    public String getEmail() {
        return (String)attributes.get("email");
    }

    @Override
    public String getName() {
        return (String)attributes.get("name");
    }
}
