package com.example.library.domain.user.repository;

import com.example.library.domain.user.enums.SocialLoginType;
import com.example.library.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserId(String userId);
    Optional<UserEntity> findByUserNo(Long userNo);
    UserEntity findByProviderAndProviderIdAndUserEmail(SocialLoginType socialLoginType, String providerId, String userEmail);
    Optional<UserEntity> findByUserEmail(String userEmail);
    void deleteByUserId(String userId);
}
