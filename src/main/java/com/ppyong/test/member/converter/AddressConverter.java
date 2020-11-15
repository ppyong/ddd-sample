package com.ppyong.test.member.converter;

import com.ppyong.test.member.domain.Address;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class AddressConverter implements AttributeConverter<Address, String> {

    @Override
    public String convertToDatabaseColumn(Address attribute) {
        if(attribute == null)
            return null;

        return attribute.getValue();
    }

    @Override
    public Address convertToEntityAttribute(String dbData) {
        if(dbData == null)
            return null;

        return new Address(dbData);
    }
}
