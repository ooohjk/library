package com.example.library.domain.rent.infrastructure;

import com.example.library.domain.rent.RentState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentHistoryRepository extends JpaRepository<RentHistoryEntity,Long> {
    Optional<RentHistoryEntity> findByManagerNoAndBookNoAndRentState(Long managerNo, Long bookNo, RentState rentState);
    RentHistoryEntity getByManagerNoAndBookNoAndRentState(Long managerNo, Long bookNo, RentState rentState);
}
