package com.example.library.user.dto;

import com.example.library.user.entity.UserEntity;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserDto {
    @NotNull
    private Long userNo;

    @NotNull
    private String userId;

    @NotNull
    private String userPwd;

    @NotNull
    private Date createdAt;

    @NotNull
    private String userName;

    private String tel;

    private String email;

    @NotNull
    private String gender;

    private Integer useFlg;

    public UserEntity toEntity() {
        return UserEntity.builder()
                .userNo(userNo)
                .userId(userId)
                .userPwd(userPwd)
                .createdDate(createdAt)
                .userName(userName)
                .tel(tel)
                .userEmail(email)
                .gender(gender)
                .useFlg(useFlg)
                .build();
    }
}
