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
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
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
                /*
                 * 우리가 서비스를 만들 때 회원가입이라는 절차를 통해 사용자를 등록하고 관리하게 됩니다. 이때 사용자가 등록한 정보가 유효한 정보인지 인증(Authentication)하는 과정은 결코 쉽지 않고 복잡한 인증 프로세스를 요구하게 됩니다.
                 * 이러한 문제를 해결하기 위해 우리는 사용자가 이미 가입한 거대 플랫폼 기업의 인증된 사용자 정보에 접근하여 정보를 얻어올 수 있습니다.
                 * 이때 사용하는 프로토콜이 바로 OAuth(Open Authrization)입니다.
                 * OAuth의 핵심은 사용자 정보를 가지고 있는 서버(구글, 카카오...)로부터 사용자 정보에 대한 접근 권한을 인가(Authorization) 받아 정보를 가져오는 것입니다.
                 * 여기서 인가(Authorization)란 사용자에게 특정 리소스나 기능에 액세스 할 수 있는 권한을 부여하는 프로세스를 말합니다.
                 */
                .oauth2Login(oauth2->oauth2
                        .authorizationEndpoint(authorizationEndpointConfig -> authorizationEndpointConfig.baseUri("/api/user/oauth2/authorize")) //인증을 위한 url이며 default url은 /oauth2/authorization/{registration}, 옆과 같이 수정 시 /api/user/oauth2/authorize/{registration}으로 접근가능
                        .userInfoEndpoint(userInfoEndpoint->userInfoEndpoint.userService((OAuth2UserService<OAuth2UserRequest, OAuth2User>) userService))) //인가된 정보를 활용하여 당사에서 활용할 수 있도록 커스터마이징
                .addFilterBefore(new JwtFilter(userService, secret), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
