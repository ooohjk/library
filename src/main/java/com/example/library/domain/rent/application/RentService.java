package com.example.library.domain.rent.application;

import com.example.library.domain.rent.application.dto.UserRentStatusResDto;

import java.util.List;

public interface RentService {
    public void createRentManager(Long userNo);
    public void rentBook(Long userNo,Long bookNo);
    public void returnBook(Long userNo,Long bookNo);
    public void extendBook(Long userNo,Long bookNo);
    public List<UserRentStatusResDto> getCurrentRentStatus(Long userNo);
}
