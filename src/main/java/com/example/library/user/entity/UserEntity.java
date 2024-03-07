package com.example.library.user.entity;

import com.example.library.user.entity.converter.SocialLoginTypeConverter;
import com.example.library.user.entity.converter.UserGradeConverter;
import com.example.library.user.enumPk.SocialLoginType;
import com.example.library.user.enumPk.UserGrade;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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

    @Column(nullable = false, unique = true)
    @Min(value = 4)
    @Max(value = 10)
    private String userId;

    @Column(nullable = false)
    @Min(value = 6)
    @Max(value = 15)
    private String userPwd;

    @Column(nullable = false)
    @Min(value = 2)
    @Max(value = 5)
    private String userName;

    private String tel;

    @Column(name = "email")
    private String userEmail;

    @Convert(converter = SocialLoginTypeConverter.class)
    private SocialLoginType provider;

    @Column(name = "provider_id")
    private String providerId;

    @ColumnDefault("M")
    private String gender;

    private Integer useFlg;

    @Column(name = "user_grade")
    @Convert(converter = UserGradeConverter.class)
    private UserGrade userGrade;

    @Builder(builderMethodName = "createOAuth2User", builderClassName = "createOAuth2User")
    public UserEntity(String userId, String userPwd, String userEmail, String userName, String providerId, SocialLoginType provider) {
        this.userId = userId;
        this.userPwd = userPwd;
        this.userEmail = userEmail;
        this.userName = userName;
        this.providerId = providerId;
        this.provider = provider;
        this.userGrade = UserGrade.OFFICIALMEMBER;
        this.useFlg = 0;
    }
}