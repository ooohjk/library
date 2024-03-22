package com.example.library.global.mail.mailForm.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SendMailReqDto {
    @NotNull
    private String mailType;
}
