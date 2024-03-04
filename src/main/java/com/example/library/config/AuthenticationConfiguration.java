//package com.example.library.config;
//
//import com.example.library.user.service.Impl.UserServiceImpl;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//@EnableWebSecurity
////@RequiredArgsConstructor
//public class AuthenticationConfiguration {
//
//    private final UserServiceImpl userServiceImpl;
//    @Value("${jwt.secret}")
//    private String secretKey;
//
//    @Autowired
//    public AuthenticationConfiguration(UserServiceImpl userServiceImpl) {
//        this.userServiceImpl = userServiceImpl;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        System.out.println("securityFilterChain");
//        return httpSecurity
//                .httpBasic(AbstractHttpConfigurer::disable)
//                .csrf(AbstractHttpConfigurer::disable)
//                .cors(withDefaults())
//                .authorizeHttpRequests((authorizeRequests) -> {
////                    authorizeRequests.requestMatchers("/users").permitAll();
//                    authorizeRequests.requestMatchers(String.valueOf(HttpMethod.POST)).authenticated();
//                    authorizeRequests.anyRequest().permitAll();
//                })
//                .sessionManagement((sessionManagement) -> {
//                    sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//                })
//                .addFilterBefore(new JwtFilter(userServiceImpl, secretKey), UsernamePasswordAuthenticationFilter.class)
//                .build();
//    }
//}
