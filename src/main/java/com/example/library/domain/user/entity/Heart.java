package com.example.library.domain.user.entity;

import com.example.library.domain.book.entity.BookEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "heart")
public class Heart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long heartNo;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "userNo")
    private UserEntity user;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "bookCode")
    private BookEntity book;

    private String regDt;

    private String regTm;

    public void setUser(UserEntity user){
        this.user = user;
    }
}
