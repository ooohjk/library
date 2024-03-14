package com.example.library.domain.user.repository;

import com.example.library.domain.book.entity.BookEntity;
import com.example.library.domain.user.entity.Heart;
import com.example.library.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    Optional<Heart> findByHeartNo(Long heartNo);
    Optional<Heart> findByUserAndAndBook(UserEntity user, BookEntity book);
}
