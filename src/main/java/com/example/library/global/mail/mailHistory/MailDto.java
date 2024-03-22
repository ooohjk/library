package com.example.library.global.mail.mailHistory;

import com.example.library.global.mail.enums.MailType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MailDto {
    private Long userNo;
    private String email;
//    private String bookName;
    private MailType mailType;
    private String content;
    private String flg;

    public MailDto(Long userNo,MailType type){
        this.userNo=userNo;
        this.mailType=type;
    };

    public MailHistoryEntity toEntity() {
        return MailHistoryEntity.builder()
                .userNo(userNo)
                .email(email)
                .content(content)
                .type(mailType.getType())
                .flg(flg)
                .build()
                ;
    }

    public void setSendFailFlg() {
        flg="X";
    }
}
