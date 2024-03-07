package com.example.library.user.dto;

import com.example.library.user.entity.UserEntity;
import com.example.library.user.enums.SocialLoginType;
import com.example.library.user.enums.UserGrade;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class UserLoginResDto {
    private Long userNo;

    private String userId;

    private String userPwd;

    private String userName;

    private String tel;

    private String userEmail;

    private SocialLoginType provider;

    private String providerId;

    private String gender;

    private Integer useFlg;

    private UserGrade userGrade;

    private String accessToken;

    public UserLoginResDto(UserEntity user,String accessToken){
        this.userNo = user.getUserNo();
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.tel = user.getTel();
        this.userEmail = user.getUserEmail();
        this.provider = user.getProvider();
        this.gender = user.getGender();
        this.useFlg = user.getUseFlg();
        this.userGrade = user.getUserGrade();
        this.providerId=user.getProviderId();
        this.accessToken= accessToken;
    }

    public static UserLoginResDto from(UserEntity user, String accessToken){
        return new UserLoginResDto(user,accessToken);
    }
}
