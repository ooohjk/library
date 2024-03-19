package com.example.library.domain.book.entity;

import com.example.library.domain.book.entity.converter.BookStateConverter;
import com.example.library.domain.book.enums.BookState;
import com.example.library.domain.review.entity.ReviewEntity;
import com.example.library.global.listener.Entity.ModifiedEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
@Table(name = "book")
public class BookEntity extends ModifiedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Long bookCode;

    @Column(nullable = false)
    private String bookName;

    @Column(nullable = false)
    private String bookAuthor;

    @Column(nullable = false)
    private String bookContent;

    @Column(nullable = false)
    @Convert(converter = BookStateConverter.class)
    private BookState bookState;

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

    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE)
    private List<ReviewEntity> review = new ArrayList<>();

    public boolean isRentAvailableBook(){
        return bookState==BookState.RENT_AVAILABLE;
    }

    public void rentSuc(){
        changeRentUnavailable();
    }
    private void changeRentUnavailable(){
        setBookState(BookState.RENT_UNAVAILABLE);
    }

    public void returnSuc(){
        changeRentAvailable();
    }
    private void changeRentAvailable(){
        setBookState(BookState.RENT_AVAILABLE);
    }
}
