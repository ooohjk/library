package com.example.library.domain.user.service.dto;

import com.example.library.domain.user.entity.Heart;
import lombok.Data;

@Data
public class HeartResponseDto {
    private Long heartNo;
    private String regDt;
    private String regTm;

    private Long bookCode;
    private String bookName;
    private String bookAuthor;
    private String bookPublisher;
    private String bookImage;



    private HeartResponseDto(Heart heart){
        this.heartNo = heart.getHeartNo();
        this.bookCode = heart.getBook().getBookCode();
        this.bookName = heart.getBook().getBookName();
        this.bookAuthor = heart.getBook().getBookAuthor();
        this.bookPublisher = heart.getBook().getBookPublisher();
        this.bookImage = heart.getBook().getBookImage();

        this.regDt = heart.getRegDt();
        this.regTm = heart.getRegTm();
    }

    public static HeartResponseDto from(Heart heart){
        return new HeartResponseDto(heart);
    }
}
