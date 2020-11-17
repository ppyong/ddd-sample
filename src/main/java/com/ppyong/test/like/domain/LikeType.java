package com.ppyong.test.like.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  LikeType {
    BOARD("B"),
    REPLY("R")
    ;

    private String type;
}
