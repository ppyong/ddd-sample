package com.ppyong.sample.member.domain;

import com.ppyong.sample.global.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    @Transient
    @Autowired
    private PasswordEncoder passwordEncoder;

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

    public Member(String loginId, String name, String nickName, String password, PhoneNumber phoneNumber, Address address, Grade grade) {
        checkPasswordValid(password);

        this.loginId = loginId;
        this.name = name;
        this.nickName = nickName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.grade = grade;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changeNickName(String nickName) {
        this.name = nickName;
    }

    public void changePassword(String password){
        checkPasswordValid(password);
        this.password = passwordEncoder.encode(password);
    }

    public void changePhoneNumber(PhoneNumber phoneNumber){
        checkPhoneNumber(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    public void changeAddress(Address address){
        this.address = address;
    }

    private void checkPasswordValid(String password){
        String regex = "";
        // TODO
    }

    private void checkPhoneNumber(PhoneNumber phoneNumber){
        // TODO
    }
}
