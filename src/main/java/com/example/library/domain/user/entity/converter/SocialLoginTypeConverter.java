package com.example.library.domain.user.entity.converter;

import com.example.library.domain.user.enums.SocialLoginType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


@Converter
public class SocialLoginTypeConverter implements AttributeConverter<SocialLoginType, String> {
    @Override
    public String convertToDatabaseColumn(SocialLoginType attribute) {
        return (attribute==null) ? null : attribute.name().toLowerCase();
    }

    @Override
    public SocialLoginType convertToEntityAttribute(String dbData) {
        return SocialLoginType.getSocialType(dbData);
    }
}
