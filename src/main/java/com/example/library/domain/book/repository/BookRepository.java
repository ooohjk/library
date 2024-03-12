package com.example.library.domain.book.repository;

import com.example.library.domain.book.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    Optional<BookEntity> findByBookAuthor(String bookAuthor);
    Optional<BookEntity> findByBookName(String bookName);
    Optional<BookEntity> findByBookCode(Long bookCode);
    void deleteByBookCode(Long bookCode);
//    void remove(Long bookCode);
}
