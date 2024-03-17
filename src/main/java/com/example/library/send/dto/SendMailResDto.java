package com.example.library.send.dto;

import com.example.library.send.entity.SendMailEntity;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@ToString
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendMailResDto {
    @NotNull
    private String mailContent;

    public void SendMailResDto(SendMailEntity sendMailEntity) {
        this.mailContent = sendMailEntity.getMailContent();
    }
}
