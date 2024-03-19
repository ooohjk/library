package com.example.library.domain.rent.infrastructure.repository;

import com.example.library.domain.rent.RentState;
import com.example.library.domain.rent.infrastructure.entity.RentHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaRentHistoryRepository extends JpaRepository<RentHistoryEntity,Long> {
    Optional<RentHistoryEntity> findByUserNoAndBookNoAndRentState(Long userNo, Long bookNo, RentState rentState);
}
