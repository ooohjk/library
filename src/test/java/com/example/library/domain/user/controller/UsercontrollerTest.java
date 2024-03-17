package com.example.library.domain.user.controller;

import com.example.library.domain.RestDocsSupport;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest(classes = {UserServiceImpl.class, UserRepository.class}) @SpringBootTest는 모든 빈을 등록한다고 하는데 classes 속성을 정의한다면 해당 클래스의 빈만 정의한다.

//웹 상의 요청과 응답에 대한 테스트 진행, 컴포넌트 스캔 범위는 Presentation 레이어에 속하는 빈들만 등록(@Controller, @ControllerAdvice, @JsonComponent, @Filter, WebMvcConfigurer, HandlerMethodArgumentResolver, MockMvc()
//주의할점: 보통 컨트롤러에선 서비스레이어의 메소드를 호출하는데 이들은 Presentation 레이어에 속하는 빈들이 아니기에 @Autowired와 같은 어노테이션은 사용불가하다. 따라서 @MockBean, mock(),spy() 등을 이용하여 해당 메소드를 mocking해줘야 한다.
@WebMvcTest(UserController.class) //UserController빈만 정의
public class UsercontrollerTest extends RestDocsSupport {

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

    @Test
    @DisplayName("요청JSON 파싱 실패 case")
    @WithMockUser(username = "테스트_최고관리자", authorities = {"0"}) //권한 부여
    void 요청필드_키밸류형태_에러처리_테스트() throws Exception{
        //given
        String content = "id:'s";

        //when
        mockMvc.perform(post("/user/join")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(content))
                )
                .andExpect(jsonPath("$.code").value("R02"))
        ;
    }

    @Test
    @DisplayName("회원가입 시 아이디 필드가 누락 및 패스워드 size 검증 실패된 경우 case")
    @WithMockUser(username = "테스트_최고관리자", authorities = {"0"}) //권한 부여
    void 아이디누락_에러처리_테스트() throws Exception{
        //given
        String id= "test";
        String pwd = "1234";
        UserJoinReqDto dto = UserJoinReqDto.builder()
//                .userId(id)
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
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(dto))
                )
                .andExpect(jsonPath("$.code").value("R01"))
        ;
    }

    @Test
    @DisplayName("restapi url 케이스인 경우 데이터 타입이 올바르지 않은 case")
    @WithMockUser(username = "테스트_최고관리자", authorities = {"0"}) //권한 부여
    void url_내_데이터타입_불일치_테스트() throws Exception{
        //given
        String id= "test";

        //when
        mockMvc.perform(get("/user/get/userNo/"+id)
                        .with(csrf()) //403에러 해결
                )
                .andExpect(jsonPath("$.code").value("R01"))
        ;
    }
}
