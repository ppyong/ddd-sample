package com.ppyong.sample.member.ui;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateReq {
    private String loginId;

    private String name;

    private String password;

    private String nickName;
}
