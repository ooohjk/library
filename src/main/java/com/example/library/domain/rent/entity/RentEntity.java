package com.example.library.domain.rent.entity;

import com.example.library.domain.book.entity.BookEntity;
import com.example.library.domain.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class RentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentNo;

    @Column(nullable = false)
    @OneToMany
    private UserEntity user;

    @Column(nullable = false)
    @OneToOne
    private BookEntity book;

    @Column(nullable = false)
    private String rentDt;

    @Column(nullable = false)
    private String prospectDt;

    @Column()
    private String returnDt;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean extension;

    @Column(nullable = false)
    private Integer rentState;
}
