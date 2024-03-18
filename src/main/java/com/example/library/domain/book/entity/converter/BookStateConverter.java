package com.example.library.domain.book.entity.converter;

import com.example.library.domain.book.enums.BookState;
import com.example.library.domain.rent.RentState;
import jakarta.persistence.AttributeConverter;

public class BookStateConverter implements AttributeConverter<BookState,Integer> {

    @Override
    public Integer convertToDatabaseColumn(BookState attribute) {
        return attribute.getStateNum();
    }

    @Override
    public BookState convertToEntityAttribute(Integer dbData) {
        return BookState.getBookState(dbData);
    }
}
