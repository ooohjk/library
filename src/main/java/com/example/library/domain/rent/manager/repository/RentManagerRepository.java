package com.example.library.domain.rent.manager.repository;

import com.example.library.domain.rent.manager.entity.RentManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentManagerRepository extends JpaRepository<RentManagerEntity, Long> {
    List<RentManagerEntity> findAll();
    RentManagerEntity findByUserUserNo(Long userNo);
}
