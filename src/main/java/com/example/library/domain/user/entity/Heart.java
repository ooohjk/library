package com.example.library.domain.user.entity;

import com.example.library.domain.book.entity.BookEntity;
import com.example.library.global.listener.Entity.BaseEntity;
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
public class Heart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long heartNo;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "userNo")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "bookCode")
    private BookEntity book;

    public void setUser(UserEntity user){
        this.user = user;
    }
}
