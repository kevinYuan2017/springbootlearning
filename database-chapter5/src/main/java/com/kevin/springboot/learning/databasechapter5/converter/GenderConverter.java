package com.kevin.springboot.learning.databasechapter5.converter;

import com.kevin.springboot.learning.databasechapter5.enumeration.Gender;

import javax.persistence.AttributeConverter;

public class GenderConverter implements AttributeConverter<Gender, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Gender gender) {
        return gender.getId();
    }

    @Override
    public Gender convertToEntityAttribute(Integer integer) {
        return Gender.getGenderById(integer);
    }
}
