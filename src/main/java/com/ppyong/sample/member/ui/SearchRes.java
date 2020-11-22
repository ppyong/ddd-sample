package com.ppyong.sample.member.ui;

import com.ppyong.sample.member.converter.AddressConverter;
import com.ppyong.sample.member.converter.PhoneNumberConverter;
import com.ppyong.sample.member.domain.Address;
import com.ppyong.sample.member.domain.Grade;
import com.ppyong.sample.member.domain.PhoneNumber;

public class SearchRes {
    private String loginId;

    private String name;

    private String nickName;

    private String phoneNumber;

    private String address;

    private Grade grade;

    public SearchRes(String loginId, String name, String nickName, PhoneNumber phoneNumber, Address address, Grade grade) {
        this.loginId = loginId;
        this.name = name;
        this.nickName = nickName;
        this.phoneNumber = new PhoneNumberConverter().convertToDatabaseColumn(phoneNumber);
        this.address = new AddressConverter().convertToDatabaseColumn(address);
        this.grade = grade;
    }
}
