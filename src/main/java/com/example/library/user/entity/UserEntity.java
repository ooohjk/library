package com.example.library.user.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user")
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo;

    private String userId;

    private String userPwd;

    private String userName;

    @Column(name = "createdAt")
    private Date createdDate;

    private String tel;

    @Column(name = "email")
    private String userEmail;

    private String gender;

    private Integer useFlg;

//    public UserDto toDto() {
//        return UserDto.builder()
//                .userNo(userNo)
//                .userId(userId)
//                .userPwd(userPwd)
//                .userName(userName)
//                .createdAt(createdDate)
//                .tel(tel)
//                .email(userEmail)
//                .gender(gender)
//                .useFlg(useFlg)
//                .build();
//    }
}