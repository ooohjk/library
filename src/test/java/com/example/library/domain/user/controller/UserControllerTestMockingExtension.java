package com.example.library.domain.user.controller;

import com.example.library.domain.user.dto.UserJoinReqDto;
import com.example.library.domain.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class) //InjectMocks,Mock 어노테이션 활성화를 위한 추가
public class UserControllerTestMockingExtension {

    @InjectMocks //Mock 객체가 주입되어야 하는 대상 객체 생성 및 주입
    private UserController userController;

    @Mock //가짜 객체를 만들어주는 어노테이션
    private UserService userService;

    private MockMvc mockMvc;

    private ObjectMapper om = new ObjectMapper();


    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
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
        ;
    }
}
