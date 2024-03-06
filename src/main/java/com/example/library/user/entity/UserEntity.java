package com.example.library.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "user")
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String userPwd;

    @Column(nullable = false)
    private String userName;

    private String tel;

    @Column(name = "email")
    private String userEmail;

    private String provider;

    private String providerId;

    @Column(nullable = false)
    private String gender;

    @Column
    private Integer useFlg;

    private Integer userLevel;


}