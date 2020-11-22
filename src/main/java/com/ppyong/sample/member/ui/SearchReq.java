package com.ppyong.sample.member.ui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SearchReq {
    private String loginId;

    private String name;

    private String nickName;
}
