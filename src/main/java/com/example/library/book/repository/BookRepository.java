package com.example.library.book.repository;

import com.example.library.book.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    Optional<BookEntity> searchByBookCode(Long bookCode);
}
