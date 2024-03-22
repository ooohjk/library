package com.example.library.global.mail.mailHistory;

import com.example.library.global.entityListener.Entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mail_history")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MailHistoryEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyNo;

    private Long userNo;

    private String email;

    private String content;

    private String type;

    private String flg;
}
