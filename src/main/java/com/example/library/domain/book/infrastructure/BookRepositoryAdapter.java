package com.example.library.domain.book.infrastructure;

import com.example.library.domain.book.domain.BookEntity;
import com.example.library.domain.book.domain.repository.BookRepository;
import com.example.library.exception.ErrorCode;
import com.example.library.exception.exceptions.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookRepositoryAdapter implements BookRepository {

    private final SpringDataJpaBookRepository bookRepository;

    @Override
    public BookEntity findByBookNo(Long bookNo) {
        BookEntity bookDomain= bookRepository.findByBookCode(bookNo)
                .orElseThrow(() -> new BookNotFoundException(ErrorCode.BOOKCODE_NOT_FOUND));
        return bookDomain;
    }

    @Override
    public BookEntity save(BookEntity book) {
        return bookRepository.save(book);
    }
}
