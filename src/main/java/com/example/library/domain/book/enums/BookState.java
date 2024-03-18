package com.example.library.domain.book.enums;


import com.example.library.exception.ErrorCode;
import com.example.library.exception.exceptions.BookStateNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum BookState {
    RENT_AVAILABLE(0,"대여 가능/사내 배치"),
    RENT_UNAVAILABLE(1,"대여 불가/대여 중")
    ;

    private final Integer stateNum;
    private final String desc ;

    public static BookState getBookState(Integer value){
        return Arrays.stream(BookState.values())
                .filter(bookState -> bookState.getStateNum().equals(value))
                .findFirst()
                .orElseThrow(()->new BookStateNotFoundException(ErrorCode.BOOKSTATE_NOT_FOUND));
    }
}
