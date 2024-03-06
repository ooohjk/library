package com.example.library.config;

import com.example.library.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class AuthenticationConfig {

    private final UserService userService;

    @Value("${jwt.secret}")
    private String secret;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    try {
                        auth.requestMatchers("/user/login", "/user/join", "/user/get", "/user/get/**", "/swagger-ui/**", "/v3/api-docs/**", "/book/search/**").permitAll()
                            .requestMatchers(HttpMethod.GET).permitAll()
                            .requestMatchers(HttpMethod.POST).authenticated();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                })
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                //2024.03.06 sunghyun oauth2 설정
                .oauth2Login(oauth2->oauth2
                        .authorizationEndpoint(authorizationEndpointConfig -> authorizationEndpointConfig.baseUri("/api/user/oauth2/authorize"))) //인증을 위한 url이며 default url은 /oauth2/authorization/{registration}, 옆과 같이 수정 시 /api/user/oauth2/authorize/{registration}으로 접근
                .addFilterBefore(new JwtFilter(userService, secret), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
