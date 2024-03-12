package com.example.library.domain.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {
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
}
