package com.example.library.user.entity;

import com.example.library.user.entity.converter.SocialLoginTypeConverter;
import com.example.library.user.entity.converter.UserGradeConverter;
import com.example.library.user.enumPk.SocialLoginType;
import com.example.library.user.enumPk.UserGrade;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user")
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo;

//    @Column(nullable = false, unique = true)
    private String userId;

//    @Column(nullable = false)
    private String userPwd;

//    @Column(nullable = false)
    private String userName;

    private String tel;

    @Column(name = "email")
    private String userEmail;

    @Convert(converter = SocialLoginTypeConverter.class)
    private SocialLoginType provider;

    @Column(name = "provider_id")
    private String providerId;

//    @Column(nullable = false)
    private String gender;

    private Integer useFlg;

    @Column(name = "user_grade")
    @Convert(converter = UserGradeConverter.class)
    private UserGrade userGrade;

    @Builder(builderMethodName = "createOAuth2User",builderClassName = "createOAuth2User")
    public UserEntity(String userEmail,String userName,String providerId,SocialLoginType provider) {
        this.userEmail= userEmail;
        this.userName= userName;
        this.providerId =providerId;
        this.provider = provider;
        this.userGrade=UserGrade.OFFICIALMEMBER;
        this.useFlg=0;
    }
}