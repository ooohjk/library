package com.example.library.domain.user.controller;

import com.example.library.config.RestDocsConfig;
import com.example.library.domain.user.dto.UserLoginReqDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.restdocs.RestDocsAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@AutoConfigureRestDocs                 // RestDoc 사용
@Import(RestDocsConfig.class)   // 정렬 부분!
class UserControllerTest {

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation(); // (1)

    @Autowired
    RestDocumentationResultHandler restDocs;


    @Autowired
    private ObjectMapper om;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;


    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .alwaysDo(MockMvcResultHandlers.print())
                .alwaysDo(restDocs)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }


    @Test
    @DisplayName("유저 로그인 테스트")
    void login() throws Exception{
        String id= "sunghyun";
        String pwd = "1234";
        UserLoginReqDto s= UserLoginReqDto.builder().userId(id).userPwd(pwd).build();

        mockMvc.perform(post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(s))
                )
                .andExpect(status().isOk())
                .andDo(restDocs.document(
                        requestFields(
                                fieldWithPath("userId").description("유저아이디"),
                                fieldWithPath("userPwd").description("유저패스워드")
                        ),
                        responseFields(
                                fieldWithPath("code").description("응답코드"),
                                fieldWithPath("msg").description("응답메시지"),
                                fieldWithPath("data.userNo").description("유저 번호"),
                                fieldWithPath("data.userId").description("아이디"),
                                fieldWithPath("data.userName").description(" 이름"),
                                fieldWithPath("data.tel").description("전화번호"),
                                fieldWithPath("data.userEmail").description("이메일"),
                                fieldWithPath("data.provider").description("소셜로그인 가입 플랫폼"),
                                fieldWithPath("data.providerId").description("소셜로그인 pk"),
                                fieldWithPath("data.gender").description("성별"),
                                fieldWithPath("data.useFlg").description("사용여부(0:사용 / 1:미사용)"),
                                fieldWithPath("data.userGrade").description("회원등급(0: 관리자, 2: 정회원"),
                                fieldWithPath("data.accessToken").description("액세스 토큰")
                        )
                ))
                .andExpect(jsonPath("$.data.userNo").value(74L))
                .andExpect(jsonPath("$.data.userId").value(id))
        ;
    }

    @Test
    void Hello_테스트() throws Exception {

        // given
        String name = "son";

        // when
        mockMvc.perform(get("/user/test/{name}",name)
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())  // then
//                .andExpect(jsonPath("$.name").value(name))
                .andDo(document("test",
                                preprocessRequest(prettyPrint()),   // (2)
                                preprocessResponse(prettyPrint()),  // (3)
                                pathParameters(
                                        parameterWithName("name").description("이름")
                                )
                                ,
                                responseFields(						// (5)
                                        fieldWithPath("name").description("사용자 이름") //
                                )
                        )
                );
    }
}