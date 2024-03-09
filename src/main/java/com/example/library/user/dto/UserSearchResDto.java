package com.example.library.user.dto;

import com.example.library.user.entity.UserEntity;
import com.example.library.user.enums.SocialLoginType;
import com.example.library.user.enums.UserGrade;
import lombok.Getter;

@Getter
public class UserSearchResDto {
    private Long userNo;

    private String userId;

    private String userName;

    private String tel;

    private String userEmail;

    private SocialLoginType provider;

    private String providerId;

    private String gender;

    private Integer useFlg;

    private UserGrade userGrade;

    private UserSearchResDto(UserEntity user) {
        this.userNo = user.getUserNo();
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.tel = user.getTel();
        this.userEmail = user.getUserEmail();
        this.provider = user.getProvider();
        this.gender = user.getGender();
        this.useFlg = user.getUseFlg();
        this.userGrade = user.getUserGrade();
        this.providerId = user.getProviderId();
    }

    public static UserSearchResDto from(UserEntity user){
        return new UserSearchResDto(user);
    }
}
