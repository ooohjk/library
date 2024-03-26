package com.example.library.domain.rent.application;

import com.example.library.domain.rent.RentState;
import com.example.library.domain.rent.application.dto.UserRentStatusResDto;
import com.example.library.domain.rent.domain.RentHistory;
import com.example.library.domain.rent.domain.RentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class RentServiceImplTest {

    @InjectMocks
    private RentServiceImpl rentService;

    @Mock
    private RentRepository rentRepository;

    @Test
    void 유저_대여현황_가져오기(){
        RentHistory rentHistory1 = new RentHistory(1L,1L,1L,1L,"20230301",null,"20230311",true, RentState.ON_RENT);
        RentHistory rentHistory2 = new RentHistory(2L,1L,1L,2L,"20230301",null,"20230311",true, RentState.ON_RENT);
        List<RentHistory> list = Arrays.asList(rentHistory1,rentHistory2);

        //given
        when(rentRepository.findUserRentStatus(1L)).thenReturn(list);

        List<UserRentStatusResDto> userRentStatusResDtos = rentService.getCurrentRentStatus(1L);
        System.out.println(userRentStatusResDtos.get(0));
        System.out.println(userRentStatusResDtos.get(1));
        Assertions.assertEquals(userRentStatusResDtos.size(),2);

    }
}