package com.example.library.user.entity.converter;

import com.example.library.user.enumPk.UserGrade;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


@Converter
public class UserGradeConverter implements AttributeConverter<UserGrade,Integer> {
    @Override
    public Integer convertToDatabaseColumn(UserGrade attribute) {
        return attribute.getGrade();
    }

    @Override
    public UserGrade convertToEntityAttribute(Integer dbData) {
        return UserGrade.getUserGrade(dbData);
    }
}
