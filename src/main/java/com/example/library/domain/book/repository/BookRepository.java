package com.example.library.domain.book.repository;

import com.example.library.domain.book.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    Optional<BookEntity> searchByBookAuthor(String bookAuthor);
    Optional<BookEntity> searchByBookName(String bookName);
}
