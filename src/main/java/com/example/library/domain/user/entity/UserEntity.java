package com.example.library.domain.user.entity;

import com.example.library.domain.review.entity.ReviewEntity;
import com.example.library.domain.user.entity.converter.SocialLoginTypeConverter;
import com.example.library.domain.user.entity.converter.UserGradeConverter;
import com.example.library.domain.user.enums.SocialLoginType;
import com.example.library.domain.user.enums.UserGrade;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName ="createOfficialUser",builderClassName = "createOfficialUser")
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

    @OneToMany(mappedBy = "user")
    private List<ReviewEntity> review = new ArrayList<>();

    @Builder(builderMethodName = "createOAuth2User", builderClassName = "createOAuth2User")
    public UserEntity (String userId, String userPwd, String userEmail, String userName, String providerId, SocialLoginType provider, Integer useFlg) {
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