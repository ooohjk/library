package com.example.library.domain.user.controller;

import com.example.library.RestDocsSupport;
import com.example.library.domain.user.dto.UserLoginReqDto;
import com.example.library.domain.user.entity.UserEntity;
import com.example.library.domain.user.enums.SocialLoginType;
import com.example.library.domain.user.enums.UserGrade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class UserControllerTest extends RestDocsSupport {

//    @Test
//    @DisplayName("유저 로그인 테스트")
//    void login() throws Exception{
//        //given
//        String id= "sunghyun7895";
//        String pwd = "1234";
//
//        UserEntity user = UserEntity.createOfficialUser()
//                .userId(id)
//                .userPwd(pwd)
//                .userName("손성현")
//                .userEmail("sunghyun7895@naver.com")
//                .gender("M")
//                .userGrade(UserGrade.OFFICIALMEMBER)
//                .useFlg(0)
//                .provider(SocialLoginType.GOOGLE)
//                .build()
//                ;
//
//        UserLoginReqDto res= UserLoginReqDto.builder().userId(id).userPwd(pwd).build();
//
//        mockMvc.perform(post("/user/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(om.writeValueAsString(res))
//                )
//                .andExpect(status().isOk())
//                .andDo(restDocs.document(
//                        requestFields(
//                                fieldWithPath("userId").description("유저아이디"),
//                                fieldWithPath("userPwd").description("유저패스워드")
//                        ),
//                        responseFields(
//                                fieldWithPath("code").description("응답코드"),
//                                fieldWithPath("msg").description("응답메시지"),
//                                fieldWithPath("data.userNo").description("유저 번호"),
//                                fieldWithPath("data.userId").description("아이디"),
//                                fieldWithPath("data.userName").description(" 이름"),
//                                fieldWithPath("data.tel").description("전화번호"),
//                                fieldWithPath("data.userEmail").description("이메일"),
//                                fieldWithPath("data.provider").description("소셜로그인 가입 플랫폼"),
//                                fieldWithPath("data.providerId").description("소셜로그인 pk"),
//                                fieldWithPath("data.gender").description("성별"),
//                                fieldWithPath("data.useFlg").description("사용여부(0:사용 / 1:미사용)"),
//                                fieldWithPath("data.userGrade").description("회원등급(0: 관리자, 2: 정회원"),
//                                fieldWithPath("data.accessToken").description("액세스 토큰")
//                        )
//                ))
//                .andExpect(jsonPath("$.data.userId").value(id))
//        ;
//    }

//    @Test
//    void Hello_테스트() throws Exception {
//
//        // given
//        String name = "son";
//
//        // when
//        mockMvc.perform(get("/user/test/{name}",name)
//                        .characterEncoding("utf-8")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())  // then
////                .andExpect(jsonPath("$.name").value(name))
//                .andDo(document("test",
//                                preprocessRequest(prettyPrint()),   // (2)
//                                preprocessResponse(prettyPrint()),  // (3)
//                                pathParameters(
//                                        parameterWithName("name").description("이름")
//                                )
//                                ,
//                                responseFields(						// (5)
//                                        fieldWithPath("name").description("사용자 이름") //
//                                )
//                        )
//                );
//    }
}