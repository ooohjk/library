package com.example.library.user.entity.converter;

import com.example.library.user.enumPk.SocialLoginType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


@Converter
public class SocialLoginTypeConverter implements AttributeConverter<SocialLoginType, String> {
    @Override
    public String convertToDatabaseColumn(SocialLoginType attribute) {
        return attribute.name().toLowerCase();
    }

    @Override
    public SocialLoginType convertToEntityAttribute(String dbData) {
        return SocialLoginType.getSocialType(dbData);
    }
}
