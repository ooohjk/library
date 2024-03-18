package com.example.library.domain.rent_history.service;

import com.example.library.domain.rent_history.dto.RentHistoryDto;

import java.text.ParseException;
import java.util.List;

public interface RentHistoryService {
    List<RentHistoryDto> getAllRent();
    List<RentHistoryDto> getMylist(Long userNo);
    RentHistoryDto add(Long userNo, Long bookCode) throws ParseException;
    RentHistoryDto returnBook(Long userNo, Long bookCode) throws ParseException;
    RentHistoryDto extension(Long userNo, Long bookCode) throws ParseException;
}
