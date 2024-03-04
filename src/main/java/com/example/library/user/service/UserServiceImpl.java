package com.example.library.user.service;

import com.example.library.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {
    @Value("${jwt.secret}")
    private String secretKey;

    private final Long expiredMs = 1000 * 60 * 60l;

    public String login(String userName, String password) {
        return JwtUtil.createJwt(userName, secretKey, expiredMs);
    }
}
