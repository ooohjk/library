package com.example.library.domain.user.controller;

import com.example.library.RestDocsSupport;
import com.example.library.domain.user.dto.UserJoinReqDto;
import com.example.library.domain.user.dto.UserLoginReqDto;
import com.example.library.domain.user.dto.UserLoginResDto;
import com.example.library.domain.user.enums.SocialLoginType;
import com.example.library.domain.user.enums.UserGrade;
import com.example.library.domain.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
//@SpringBootTest(classes = {UserServiceImpl.class, UserRepository.class})
public class UsercontrollerTest extends RestDocsSupport {

//    @Autowired
    @MockBean
    UserService userService;

    @Test
    @DisplayName("회원가입 성공 case")
    @WithMockUser(username = "테스트_최고관리자", authorities = {"0"}) //권한 부여
    void 회원가입_성공() throws  Exception{
        //given
        String id= "test";
        String pwd = "1234";
        UserJoinReqDto dto = UserJoinReqDto.builder()
                .userId(id)
                .userPwd(pwd)
                .userName("테스트")
                .tel("01024168946")
                .email("sunghyun7895@naver.com")
                .gender("M")
                .useFlg(0)
                .build()
        ;

        //when
        mockMvc.perform(post("/user/join")
                .with(csrf()) //403에러 해결
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(dto))
        )

        //then
        .andExpect(status().isOk())
        .andDo(restDocs.document(
                    requestFields(
                            fieldWithPath("userId").description("아이디"),
                            fieldWithPath("userPwd").description("비밀번호"),
                            fieldWithPath("userName").description("이름"),
                            fieldWithPath("tel").description("전화번호").optional(),
                            fieldWithPath("email").description("이메일"),
                            fieldWithPath("gender").description("성별"),
                            fieldWithPath("useFlg").description("사용 여부")
                    ),
                    responseFields(
                            fieldWithPath("code").description("응답코드"),
                            fieldWithPath("msg").description("응답메시지")
                    )
                )
        )
                ;
    }

    @Test
    @DisplayName("로그인 성공 case")
    @WithMockUser(username = "테스트_최고관리자", authorities = {"0"}) //권한 부여
    void 로그인_성공() throws Exception{
        //given
        String id= "test";
        String pwd = "1234";

        UserLoginReqDto loginReqDto=
                UserLoginReqDto.builder().userId(id).userPwd(pwd).build();

        UserLoginResDto loginResDto = UserLoginResDto.builder()
                                .userNo(1L)
                                .userId(id)
                                .userName("테스트")
                                .gender("M")
                                .tel("01012341234")
                                .userEmail("sunghyun7895@naver.com")
                                .useFlg(0)
                                .provider(SocialLoginType.GOOGLE)
                                .providerId("12039812908")
                                .userGrade(UserGrade.OFFICIALMEMBER)
                                .accessToken("eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJ0ZXN0IiwiaWF0IjoxNzEwMTYwMjg3LCJleHAiOjE3MTAxNjM4ODd9.IBgXUSnlaOhwuZ1FnD1gK1TCY7ykPn6UC8YCSj-Baj0")
                        .build();

        given(userService.login(any(UserLoginReqDto.class))).willReturn(loginResDto);


        mockMvc.perform(post("/user/login")
                 .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(om.writeValueAsString(loginReqDto))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.userNo").value(1L))
                .andExpect(jsonPath("$.data.userId").value(id))
                .andDo(
                        restDocs.document(
                                requestFields(
                                        fieldWithPath("userId").description("아이디"),
                                        fieldWithPath("userPwd").description("비밀번호")
                                ),
                                responseFields(
                                fieldWithPath("code").description("응답코드"),
                                fieldWithPath("msg").description("응답메시지"),
                                fieldWithPath("data.userNo").description("유저 번호"),
                                fieldWithPath("data.userId").description("아이디"),
                                fieldWithPath("data.userName").description(" 이름"),
                                fieldWithPath("data.tel").description("전화번호").optional(),
                                fieldWithPath("data.userEmail").description("이메일"),
                                fieldWithPath("data.provider").description("소셜로그인 가입 플랫폼(GOOGLE,NAVER,KAKAO)").optional(),
                                fieldWithPath("data.providerId").description("소셜로그인 pk").optional(),
                                fieldWithPath("data.gender").description("성별"),
                                fieldWithPath("data.useFlg").description("사용여부(0:사용 / 1:미사용)"),
                                fieldWithPath("data.userGrade").description("회원등급(0: 관리자, 2: 정회원)"),
                                fieldWithPath("data.accessToken").description("액세스 토큰")
                        )
                        )
                )
        ;
    }
}
