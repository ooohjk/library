package com.example.library.global.mail.mailForm.repository;

import com.example.library.global.mail.mailForm.entity.MailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MailRepository extends JpaRepository<MailEntity, Long> {
    Optional<MailEntity> findByMailType(String type);
}
