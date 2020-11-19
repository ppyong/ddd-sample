package com.ppyong.test.member.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends AbstractAggregateRoot {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String loginId;

    private String name;

    private String nickName;

    private String password;

    private PhoneNumber phoneNumber;

    private Address address;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    public void changePassword(String password){
        checkPasswordValid(password);
        this.password = password;
    }

    public void changePhoneNumber(PhoneNumber phoneNumber){
        checkPhoneNumber(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    private void checkPasswordValid(String password){
        // TODO
    }

    private void checkPhoneNumber(PhoneNumber phoneNumber){
        // TODO
    }
}
