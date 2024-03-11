package com.example.library;

import com.example.library.config.RestDocsConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;


/*
 * 각 테스트컨트롤러마다 공통적으로 필요한 설정 및 필드들 합쳐주는 추상클래스
 */
//@AutoConfigureMockMvc
//@ExtendWith(SpringExtension.class)
@Import(RestDocsConfig.class)
@AutoConfigureRestDocs
@MockBean(JpaMetamodelMappingContext.class)
@RunWith(SpringRunner.class)
public abstract class RestDocsSupport {

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation(); // (1)

    @Autowired
    protected RestDocumentationResultHandler restDocs;

    @Autowired
    protected ObjectMapper om;

    @Autowired
    protected MockMvc mockMvc;

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
}
