package com.example.library.domain.rent.infrastructure.entity.converter;

import com.example.library.domain.rent.RentState;
import jakarta.persistence.AttributeConverter;

public class RentStateConverter implements AttributeConverter<RentState,Integer> {

    @Override
    public Integer convertToDatabaseColumn(RentState attribute) {
        return attribute.getStateNum();
    }

    @Override
    public RentState convertToEntityAttribute(Integer dbData) {
        return RentState.getRentState(dbData);
    }
}
