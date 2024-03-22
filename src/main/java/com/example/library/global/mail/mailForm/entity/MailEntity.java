package com.example.library.global.mail.mailForm.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "mail")
public class MailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mailNo;

    @Column(nullable = false)
    private String mailType;

    @Column(nullable = false)
    private String mailContent;
}
