package com.example.library.domain.rent.repository;

import com.example.library.domain.rent.entity.RentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRepository extends JpaRepository<RentEntity, Long> {

}
