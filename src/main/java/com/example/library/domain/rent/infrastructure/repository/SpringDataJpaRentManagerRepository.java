package com.example.library.domain.rent.infrastructure.repository;

import com.example.library.domain.rent.infrastructure.entity.RentManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaRentManagerRepository extends JpaRepository<RentManagerEntity,Long> {
    Optional<RentManagerEntity> findByUserNo(Long userNo);
}
