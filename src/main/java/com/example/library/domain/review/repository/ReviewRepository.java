package com.example.library.domain.review.repository;

import com.example.library.domain.review.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    List<ReviewEntity> findAll();
    List<ReviewEntity> findAllByUserUserId(String userId);
    List<ReviewEntity> findAllByBookBookCode(Long bookCode);
    @Modifying
    @Query(value = "update ReviewEntity r set r.user.userId = :unknown where r.user.userId = :userId")
    void update(String userId, String unknown);
    @Modifying
    @Query(value = "delete from ReviewEntity r where r.book.bookCode = :bookCode")
    void deleteByBookBookCode(Long bookCode);
}
