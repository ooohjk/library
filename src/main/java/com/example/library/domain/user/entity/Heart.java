package com.example.library.domain.user.entity;

import com.example.library.domain.book.entity.BookEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Heart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long heartNo;

//    @Column(nullable = false)
//    private Long userNo;

//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_no")
    private UserEntity user;

//    @Column(nullable = false)
//    private Long bookCode;

    @ManyToOne
    @JoinColumn(name = "book_code")
    private BookEntity book;


    private String regDt;

    private String regTm;

    public void setUser(UserEntity user){
        this.user=user;
    }
}
