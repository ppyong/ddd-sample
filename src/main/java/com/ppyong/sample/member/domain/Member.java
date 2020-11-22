package com.ppyong.sample.member.domain;

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

    @Column(length = 20, nullable = false, unique = true)
    private String loginId;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 20, nullable = false, unique = true)
    private String nickName;

    @Column(nullable = false)
    private String password;

    private PhoneNumber phoneNumber;

    private Address address;

    @Enumerated(EnumType.STRING)
    private Grade grade = Grade.USER;

    public void changePassword(String password){
        checkPasswordValid(password);
        this.password = password;
    }

    public void changePhoneNumber(PhoneNumber phoneNumber){
        checkPhoneNumber(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    private void checkPasswordValid(String password){
        String regex = "";
        // TODO
    }

    private void checkPhoneNumber(PhoneNumber phoneNumber){
        // TODO
    }
}
