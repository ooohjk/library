package com.example.library.domain.rent.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RentManagerRepository extends JpaRepository<RentManagerEntity,Long> {
    Optional<RentManagerEntity> findByUserNo(Long userNo);
}
