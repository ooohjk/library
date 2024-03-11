package com.example.library.global.security.login.filter;

import com.example.library.domain.user.dto.UserSearchResDto;
import com.example.library.domain.user.service.UserService;
import com.example.library.global.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        UsernamePasswordAuthenticationToken authenticationToken= null;

        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authorization : {}", authorization);

        //token 없으면 block
        if(authorization == null || !authorization.startsWith("Bearer ")) {
            log.error("authorization is null || authorization is not Bearer");
            filterChain.doFilter(request, response);
            return; //이 뒤에 필터를 안타면 내가 원하는 url에 갈 수 잇나?
        }

        //token 꺼내기
        String token = authorization.split(" ")[1];

        //token expired ?
        if(JwtUtil.isExpired(token)) {
            log.error("Token is expired..");
            filterChain.doFilter(request, response);
            return;
        }

        //UserId Token 에서 꺼내기
        String userId = JwtUtil.getUserId(token);
        log.info("userId : {}", userId);

        //서명 정상적으로 됨
        if(StringUtils.hasText(userId)){
             UserSearchResDto userSearchResDto = userService.getUserByUserId(userId);
            //Authentication객체 생성
             authenticationToken = new UsernamePasswordAuthenticationToken(userId, null, List.of(new SimpleGrantedAuthority(String.valueOf(userSearchResDto.getUserGrade().getGrade()))));
        }

        // Detail 넣어주기
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
