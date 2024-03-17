package com.example.library.domain.user.dto;

import com.example.library.domain.user.entity.UserEntity;
import com.example.library.domain.user.enums.UserGrade;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserJoinReqDto {

    @NotNull
    private String userId;

    @NotNull
    @Size(min = 6, max = 15, message = "이름은 6글자 이상, 15글자 이하로 입력해주세요.")
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
