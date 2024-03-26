package com.example.library.domain.user.repository;

import com.example.library.domain.user.service.dto.UserRentStatusResDto;
import com.example.library.global.response.ApiResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="userOpenFeignClient", url="http://localhost:8000")
public interface UserOpenFeignClient {

    @GetMapping("/rent/currentRentStatus/user/{userNo}")
    public ApiResponseDto<List<UserRentStatusResDto>> getCurrentRentStatus(@PathVariable Long userNo);

}
