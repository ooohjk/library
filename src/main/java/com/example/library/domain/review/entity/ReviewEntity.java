package com.example.library.domain.review.entity;

import com.example.library.domain.book.entity.BookEntity;
import com.example.library.global.listener.Entity.BaseEntity;
import com.example.library.domain.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "review")
public class ReviewEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewNo;

    @Column(nullable = false)
    private String reviewContent;

    @ManyToOne()
    @JoinColumn(name = "bookCode", nullable = false)
    private BookEntity book;

    @ManyToOne()
    @JoinColumn(name = "userId", nullable = false, referencedColumnName = "userId")
    private UserEntity user;
}
