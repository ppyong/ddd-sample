package com.ppyong.sample.member.ui;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Optional;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PatchReq {
    private Optional<@NotEmpty String> name;

    private Optional<@NotEmpty String> password;

    private Optional<@NotEmpty String> nickName;

    private Optional<@NotEmpty String> address;

    private Optional<@NotEmpty @Size(min = 11, max = 12) String> phoneNumber;

    public void setName(String name){
        this.name = Optional.ofNullable(name);
    }

    public void setPassword(String password){
        this.password = Optional.ofNullable(password);
    }

    public void setNickName(String nickName){
        this.nickName = Optional.ofNullable(nickName);
    }

    public void setAddress(String address){
        this.address = Optional.ofNullable(address);
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = Optional.ofNullable(phoneNumber);
    }
}
