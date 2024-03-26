package com.example.library.domain.book.infrastructure;

import com.example.library.domain.book.domain.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SpringDataJpaBookRepository extends JpaRepository<BookEntity, Long> {
//    Optional<BookEntity> findByBookAuthor(String bookAuthor);
//    Optional<BookEntity> findByBookName(String bookName);
    Optional<BookEntity> findByBookCode(Long bookCode);
}
