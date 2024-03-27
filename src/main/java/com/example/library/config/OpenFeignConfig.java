package com.example.library.config;

import feign.RequestInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
@EnableFeignClients(basePackages = "com.example.library")
@RequiredArgsConstructor
public class OpenFeignConfig {

    private final HttpServletRequest request;

    //아마 모든 OpenFeignClient에 대해 세팅 될 건데, 추후 개별 세팅 가능한지 추가 확인 필요
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));
        };
    }
}
