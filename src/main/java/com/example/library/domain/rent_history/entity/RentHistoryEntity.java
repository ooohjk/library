package com.example.library.domain.rent_history.entity;

import com.example.library.domain.book.entity.BookEntity;
import com.example.library.domain.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rentHistory")
@Builder
public class RentHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentNo;

    @ManyToOne()
    @JoinColumn(nullable = false, name = "userNo")
    private UserEntity user;

    @ManyToOne()
    @JoinColumn(nullable = false, name = "bookCode")
    private BookEntity book;

    @Column(nullable = false)
    private String rentDt;

    @Column(nullable = false)
    private String prospectDt;

    @Column()
    private String returnDt;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean extension;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private Integer rentState;
}
