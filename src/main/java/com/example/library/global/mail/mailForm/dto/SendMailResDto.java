package com.example.library.global.mail.mailForm.dto;

import com.example.library.global.mail.mailForm.entity.MailEntity;
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

    public void SendMailResDto(MailEntity mailEntity) {
        this.mailContent = mailEntity.getMailContent();
    }
}
