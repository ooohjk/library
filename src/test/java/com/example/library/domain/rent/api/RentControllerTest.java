package com.example.library.domain.rent.api;

import com.example.library.domain.RestDocsSupport;
import com.example.library.domain.rent.RentState;
import com.example.library.domain.rent.application.RentService;
import com.example.library.domain.rent.application.dto.UserRentStatusResDto;
import com.example.library.domain.rent.domain.RentHistory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(RentController.class)
class RentControllerTest extends RestDocsSupport {

    @MockBean
    RentService rentService;

    @Test
    @WithMockUser(username = "테스트_최고관리자", authorities = {"0"}) //권한 부여
    void 유저_대여현황_가져오기() throws Exception {
        //given
        Long userNo=1L;
        RentHistory rentHistory1 = new RentHistory(1L,1L,1L,1L,"20230301",null,"20230311",true, RentState.ON_RENT);
        RentHistory rentHistory2 = new RentHistory(2L,1L,1L,2L,"20230301",null,"20230311",true, RentState.ON_RENT);
        List<RentHistory> list = Arrays.asList(rentHistory1,rentHistory2);
        List<UserRentStatusResDto> userRentStatusResDtos = list.stream()
                        .map(rentHistory -> UserRentStatusResDto.from(rentHistory))
                        .collect(Collectors.toList());

        //when
        when(rentService.getCurrentRentStatus(userNo)).thenReturn(userRentStatusResDtos);

        //then
        mockMvc.perform(get("/rent/currentRentStatus/user/1")
                .with(csrf())
        )
        .andExpect(jsonPath("$.data[0].bookNo").value(1L))
        .andExpect(jsonPath("$.data[1].bookNo").value(2L))
        ;

    }
}