package com.example.library.test;

import com.example.library.RestDocsSupport;
import com.example.library.exception.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.PayloadSubsectionExtractor;

import java.util.Arrays;
import java.util.Map;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.attributes;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
class ErrorCodeControllerTest extends RestDocsSupport {

//    @Test
//    @DisplayName("에러코드 문서화")
//    void errorCodes() throws Exception {
//        mockMvc.perform(get("/error-code"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.errorCodes.0").value("OK"))
//                .andDo(restDocs.document(
//                        responseFields(
//                                fieldWithPath("errorCodes."+ErrorCode.SUC.getCode()).description(ErrorCode.SUC.getMsg()),
//                                fieldWithPath("errorCodes."+ErrorCode.USERID_DUPLICATED.getCode()).description(ErrorCode.USERID_DUPLICATED.getMsg()),
//                                fieldWithPath("errorCodes."+ErrorCode.USERID_NOT_FOUND.getCode()).description(ErrorCode.USERID_NOT_FOUND.getMsg()),
//                                fieldWithPath("errorCodes."+ErrorCode.PASSWORD_DIFFERNET.getCode()).description(ErrorCode.PASSWORD_DIFFERNET.getMsg()),
//                                fieldWithPath("errorCodes."+ErrorCode.BOOKNAME_NOT_FOUND.getCode()).description(ErrorCode.BOOKNAME_NOT_FOUND.getMsg()),
//                                fieldWithPath("errorCodes."+ErrorCode.BOOKAUTHOR_NOT_FOUND.getCode()).description(ErrorCode.BOOKAUTHOR_NOT_FOUND.getMsg()),
//                                fieldWithPath("errorCodes."+ErrorCode.MAIL_NOT_FOUND.getCode()).description(ErrorCode.MAIL_NOT_FOUND.getMsg())
//                        )
//                        )
//
//                )
//                ;
//    }

    @Test
    @DisplayName("에러코드 문서화v2")
    void errorCodesV2() throws Exception {
        mockMvc.perform(get("/error-code/dto"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCodes.0").value("OK"))
                .andDo(restDocs.document(
                        codeResponseFields("code-response", beneathPath("errorCodes"),
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

    public static CodeResponseFieldsSnippet codeResponseFields(String type,
                                                               PayloadSubsectionExtractor<?> subsectionExtractor,
                                                               Map<String, Object> attributes, FieldDescriptor... descriptors) {
        return new CodeResponseFieldsSnippet(type, subsectionExtractor, Arrays.asList(descriptors), attributes
                , true);
    }
}