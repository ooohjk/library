package com.example.library.utils;

import com.example.library.global.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    @Test
    public void 토큰생성테스트(){
        String token = JwtUtil.createJwt("son");
        assertTrue(StringUtils.hasText(token));
    }

}