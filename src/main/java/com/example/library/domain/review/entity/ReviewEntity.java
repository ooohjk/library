package com.example.library.domain.review.entity;

import com.example.library.domain.book.entity.BookEntity;
import com.example.library.domain.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "review")
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewNo;

    @Column(nullable = false)
    private Date regDate;

    @Column(nullable = false)
    private String reviewContent;

    @ManyToOne
    @JoinColumn(name = "bookCode", nullable = false)
    private BookEntity book;

    @ManyToOne
    @JoinColumn(name = "userNo", nullable = false)
    private UserEntity user;
}
