package com.example.library.send.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "mail")
public class SendMailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mailNo;

    @Column(nullable = false)
    private String mailType;

    @Column(nullable = false)
    private String mailContent;
}
