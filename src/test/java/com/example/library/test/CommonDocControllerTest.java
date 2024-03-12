package com.example.library.test;

import com.example.library.domain.RestDocsSupport;
import com.example.library.exception.ErrorCode;
import com.example.library.test.snippet.CustomResponseFieldsSnippet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.PayloadSubsectionExtractor;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Arrays;
import java.util.Map;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.attributes;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CommonDocController.class)
class CommonDocControllerTest extends RestDocsSupport {

    @Test
    @WithMockUser(username = "테스트_최고관리자", authorities = {"0"}) //권한 부여
    @DisplayName("Dto 응답에 대한 에러코드 문서화v2")
    void errorCodesV2() throws Exception {
        mockMvc.perform(get("/test/error-code/dto"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCodes.0").value("OK"))
                .andDo(restDocs.document(
                            customResponseFields("code-response",
                                    beneathPath("errorCodes"),
                                    attributes(key("title").value("에러 코드 모음")),
                                    enumConvertFieldDescriptor(ErrorCode.values())
                        )
                )
                )
        ;
    }

    @Test
    @WithMockUser(username = "테스트_최고관리자", authorities = {"0"}) //권한 부여
    @DisplayName("MAP 응답에 대한 에러코드 문서화")
    void errorCodesV3() throws Exception {
        mockMvc.perform(get("/test/error-code/map"))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.0").value("OK"))
                .andDo(restDocs.document(
                                    customResponseFields("code-response",
                                    attributes(key("title").value("에러 코드 모음")),
                                    enumConvertFieldDescriptor(ErrorCode.values())
                                )
                        )
                )
        ;
    }

    private FieldDescriptor[] enumConvertFieldDescriptor(ErrorCode[] errorCodes) {
        return Arrays.stream(errorCodes)
                .map(enumType -> fieldWithPath(enumType.getCode()).description(enumType.getMsg()))
                .toArray(FieldDescriptor[]::new);
    }



    @Test
    @WithMockUser(username = "테스트_최고관리자", authorities = {"0"}) //권한 부여
    @DisplayName("응답필드 내 공통 필드 정의")
    void 공통필드문서화() throws Exception{
        mockMvc.perform(get("/test/commonFormatInRespnse"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("OK"))
                .andDo(restDocs.document(
                        customResponseFields("custom-response",
                                attributes(key("title").value("공통응답")),
                                fieldWithPath("code").description("응답 코드"),
                                fieldWithPath("msg").description("응답 메시지"),
                                fieldWithPath("data").description("응답 데이터").optional()
                    )
                ))
        ;
    }

    public static CustomResponseFieldsSnippet customResponseFields(String type,Map<String, Object> attributes, FieldDescriptor... descriptors) {
        return new CustomResponseFieldsSnippet(
                type,
                Arrays.asList(descriptors),
                attributes,
                true
        );
    }

    public static CustomResponseFieldsSnippet customResponseFields(String type, PayloadSubsectionExtractor<?> subsectionExtractor, Map<String, Object> attributes, FieldDescriptor... descriptors) {
        return new CustomResponseFieldsSnippet(
                type,
                subsectionExtractor,
                Arrays.asList(descriptors),
                attributes,
                true);
    }



}