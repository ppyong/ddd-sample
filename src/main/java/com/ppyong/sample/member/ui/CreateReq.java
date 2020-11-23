package com.ppyong.sample.member.ui;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateReq {
    @NotEmpty
    private String loginId;

    @NotEmpty
    private String name;

    @NotEmpty
    private String password;

    @NotEmpty
    private String nickName;

    @NotEmpty
    private String address;

    @NotEmpty
    @Size(min = 11, max = 12)
    private String phoneNumber;
}
