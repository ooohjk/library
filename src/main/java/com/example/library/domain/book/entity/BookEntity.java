package com.example.library.domain.book.entity;

import com.example.library.domain.review.entity.ReviewEntity;
import com.example.library.domain.user.entity.Heart;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "book")
public class BookEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookCode;

    @Column(nullable = false)
    private String bookName;

    @Column(nullable = false)
    private String bookAuthor;

    @Column(nullable = false)
    private String bookContent;

    @Column(nullable = false)
    private Integer bookState;

    @Column(nullable = false)
    private String bookPublisher;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private Date pubDate;

    @Column(nullable = false)
    private String bookLocation;

    @Column()
    private String bookImage;

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    private List<ReviewEntity> review = new ArrayList<>();

//    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
//    private List<Heart> heartList = new ArrayList<>();
}
