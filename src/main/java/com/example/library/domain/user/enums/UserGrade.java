package com.example.library.domain.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum UserGrade {
    ADMIN(0,"관리자"),
    REGULARMEMBER(1,"일반회원"),
    OFFICIALMEMBER(2,"정회원"),
    NONE(-1, "탈퇴회원");

    private final Integer grade;
    private final String gradeEx;

    public static UserGrade getUserGrade(Integer dbData) {
        return Arrays.stream(UserGrade.values())
                .filter(v -> v.getGrade().equals(dbData))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원등급 코드입니다."));
    }

    public String getUserGradeInString(){
        return this.getGrade().toString();
    }
}
