package com.example.library.global.security.oauth2.handler;

import com.example.library.global.security.oauth2.principal.CustomOAuth2User;
import com.example.library.domain.user.dto.UserLoginResDto;
import com.example.library.domain.user.entity.UserEntity;
import com.example.library.global.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomOAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess (HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("OAuth2 로그인 성공");

        //1. 유저 이메일 추출
        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        UserEntity user = customOAuth2User.getUser();

        //2. "유저이메일"을 통한 accessToken생성
        String accessToken = JwtUtil.createJwt(user.getUserEmail());

        ObjectMapper om = new ObjectMapper();
        response.addHeader("Content-Type", "application/json; charset=UTF-8");

        om.writeValue(response.getOutputStream(), UserLoginResDto.from(user,accessToken));
    }
}
