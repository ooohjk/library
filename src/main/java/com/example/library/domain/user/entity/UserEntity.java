package com.example.library.domain.user.entity;

import com.example.library.domain.review.entity.ReviewEntity;
import com.example.library.domain.user.entity.converter.SocialLoginTypeConverter;
import com.example.library.domain.user.entity.converter.UserGradeConverter;
import com.example.library.domain.user.enums.SocialLoginType;
import com.example.library.domain.user.enums.UserGrade;
import com.example.library.global.listener.Entity.ModifiedEntity;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName ="createOfficialUser",builderClassName = "createOfficialUser")
@Table(name = "user")
@DynamicInsert
public class UserEntity extends ModifiedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo;

    @Column(nullable = false, unique = true)
    @Size(min = 4, max = 15, message = "이름은 4글자 이상, 15글자 이하로 입력해주세요.")
    private String userId;

    @Column(nullable = false)
//    @Size(min = 6, max = 15, message = "이름은 6글자 이상, 15글자 이하로 입력해주세요.")
    private String userPwd;

    @Column(nullable = false)
    @Size(min = 2, max = 5, message = "이름은 2글자 이상, 5글자 이하로 입력해주세요.")
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

    @OneToMany(mappedBy = "user")
    private List<Heart> heartList = new ArrayList<>();

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

    public void heartBook(Heart heart) {
        this.heartList.add(heart);
//        heart.setUser(this); //Heart엔티티에서 선언(빌더 부분)하므로 주석처리
    }
}