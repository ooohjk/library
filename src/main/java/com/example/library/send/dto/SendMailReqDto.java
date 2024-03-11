package com.example.library.send.dto;

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
