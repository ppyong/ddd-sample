package com.ppyong.sample.member.converter;

import com.ppyong.sample.member.domain.PhoneNumber;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class PhoneNumberConverter implements AttributeConverter<PhoneNumber, String> {

    @Override
    public String convertToDatabaseColumn(PhoneNumber attribute) {
        if(attribute == null)
            return null;

        return attribute.getFirst() + attribute.getMiddle() + attribute.getLast();
    }

    @Override
    public PhoneNumber convertToEntityAttribute(String dbData) {
        if(dbData == null)
            return null;

        String first = dbData.substring(0,3);
        String middle = "";
        String last = "";
        if(dbData.length() == 11) {
            //0171112222
            middle = dbData.substring(3, 6);
            last = dbData.substring(6);
        }else{
            //01711112222
            middle = dbData.substring(3, 7);
            last = dbData.substring(7);
        }
        return new PhoneNumber(first, middle, last);
    }
}
