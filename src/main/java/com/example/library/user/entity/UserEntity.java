package com.example.library.user.entity;

import com.example.library.user.entity.converter.SocialLoginTypeConverter;
import com.example.library.user.entity.converter.UserGradeConverter;
import com.example.library.user.enumPk.SocialLoginType;
import com.example.library.user.enumPk.UserGrade;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user")
@DynamicInsert
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo;

    @Column(nullable = false, unique = true)
    @Size(min = 4, max = 10)
    private String userId;

    @Column(nullable = false)
    @Size(min = 6, max = 15)
    private String userPwd;

    @Column(nullable = false)
    @Size(min = 2, max = 5)
    private String userName;

    private String tel;

    @Column(nullable = false, name = "email")
    private String userEmail;

    @Convert(converter = SocialLoginTypeConverter.class)
    private SocialLoginType provider;

    @Column(name = "provider_id")
    private String providerId;

    @ColumnDefault("'M'")
    private String gender;

    @ColumnDefault("0")
    @Column(nullable = false)
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