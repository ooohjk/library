package com.example.library.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserLoginDto {

    @NotNull
    private String userId;

    @NotNull
    private String userPwd;
}
