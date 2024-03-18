package com.example.library.domain.rent.application;

public interface RentService {
    public void createRentManager(Long userNo);
    public void rentBook(Long userNo,Long bookNo);
    }
