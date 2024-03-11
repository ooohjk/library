package com.example.library.user.repository;

import com.example.library.user.entity.UserEntity;
import com.example.library.user.enums.SocialLoginType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserId(String userId);
    Optional<UserEntity> findByUserNo(Long userNo);
    UserEntity findByProviderAndProviderIdAndUserEmail(SocialLoginType socialLoginType, String providerId, String userEmail);
    Optional<UserEntity> findByUserEmail(String userEmail);
}
