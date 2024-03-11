package com.example.library.test;

import com.example.library.config.RestDocsConfig;
import com.example.library.exception.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@AutoConfigureRestDocs(uriPort = 8000)                 // RestDoc 사용
@Import(RestDocsConfig.class)   // 정렬 부분!
class ErrorCodeControllerTest {

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
    @DisplayName("에러코드 문서화")
    void errorCodes() throws Exception {
        mockMvc.perform(get("/error-code"))
                .andExpect(status().isOk())
                .andDo(restDocs.document(
                        responseFields(
                                fieldWithPath(ErrorCode.SUC.getCode()).description(ErrorCode.SUC.getMsg()),
                                fieldWithPath(ErrorCode.USERID_DUPLICATED.getCode()).description(ErrorCode.USERID_DUPLICATED.getMsg()),
                                fieldWithPath(ErrorCode.USERID_NOT_FOUND.getCode()).description(ErrorCode.USERID_NOT_FOUND.getMsg()),
                                fieldWithPath(ErrorCode.PASSWORD_DIFFERNET.getCode()).description(ErrorCode.PASSWORD_DIFFERNET.getMsg()),
                                fieldWithPath(ErrorCode.BOOKNAME_NOT_FOUND.getCode()).description(ErrorCode.BOOKNAME_NOT_FOUND.getMsg()),
                                fieldWithPath(ErrorCode.BOOKAUTHOR_NOT_FOUND.getCode()).description(ErrorCode.BOOKAUTHOR_NOT_FOUND.getMsg())
                        )
                        )

                )
                ;
    }
}