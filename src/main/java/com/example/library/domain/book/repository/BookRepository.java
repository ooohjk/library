package com.example.library.domain.book.repository;

import com.example.library.domain.book.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    Optional<BookEntity> findByBookAuthor(String bookAuthor);
    Optional<BookEntity> findByBookName(String bookName);
    Optional<BookEntity> findByBookCode(Long bookCode);
    @Modifying
    @Query(value = "delete from BookEntity b where b.bookCode = :bookCode")
    void deleteByBookCode(Long bookCode);
}
