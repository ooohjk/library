package com.example.library.domain.user.repository;

import com.example.library.domain.book.domain.BookEntity;
import com.example.library.domain.user.entity.Heart;
import com.example.library.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    Optional<Heart> findByUserAndBook(UserEntity user, BookEntity book);

    @Modifying
    @Query(value = "delete from Heart h where h.book.bookCode = :bookCode")
    void deleteByBookBookCode(Long bookCode);

    List<Heart> findAllByUserUserNo(Long userNo);

    @Modifying
    @Query(value = "delete from Heart h where h.user.userNo = :userNo")
    void deleteByUserUserNo(Long userNo);
}
