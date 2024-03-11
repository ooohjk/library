package com.example.library.send.repository;

import com.example.library.send.entity.SendMailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SendMailRepository extends JpaRepository<SendMailEntity, Long> {
    Optional<SendMailEntity> findByMailType(String type);
}
