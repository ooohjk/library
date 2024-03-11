package com.example.library.test;

import com.example.library.RestDocsSupport;
import com.example.library.exception.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.payload.FieldDescriptor;

import java.util.Arrays;
import java.util.Map;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.snippet.Attributes.attributes;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
class ErrorCodeControllerTest extends RestDocsSupport {

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
                                fieldWithPath(ErrorCode.BOOKAUTHOR_NOT_FOUND.getCode()).description(ErrorCode.BOOKAUTHOR_NOT_FOUND.getMsg()),
                                fieldWithPath(ErrorCode.MAIL_NOT_FOUND.getCode()).description(ErrorCode.MAIL_NOT_FOUND.getMsg())
                        )
                        )

                )
                ;
    }
}