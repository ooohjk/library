package com.example.library.domain.user.dto;

import com.example.library.domain.user.entity.UserEntity;
import com.example.library.domain.user.enums.UserGrade;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserJoinReqDto {

    @NotNull
    private String userId;

    @NotNull
    private String userPwd;

    @NotNull
    private String userName;

    private String tel;

    @NotNull
    private String email;

    @NotNull
    private String gender;

    private Integer useFlg;

    public UserEntity toUserEntity() {
        return UserEntity.createOfficialUser()
                .userId(userId)
                .userPwd(userPwd)
                .userName(userName)
                .tel(tel)
                .userEmail(email)
                .gender(getGender())
                .useFlg(useFlg)
                .userGrade(UserGrade.OFFICIALMEMBER)
                .build()
       ;
    }
}
