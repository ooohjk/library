package com.example.library.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

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
    private String userName;

    private String tel;

    private String email;

    @NotNull
    private String gender;

    private Integer useFlg;
}
