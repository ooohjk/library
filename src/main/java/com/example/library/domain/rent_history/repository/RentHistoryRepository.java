package com.example.library.domain.rent_history.repository;

import com.example.library.domain.rent_history.entity.RentHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentHistoryRepository extends JpaRepository<RentHistoryEntity, Long> {
    List<RentHistoryEntity> findAll();
    List<RentHistoryEntity> findAllByUserUserNo(Long userNo);
    List<RentHistoryEntity> findAllByUserUserNoAndBookBookCode(Long userNo, Long bookCode);
}
