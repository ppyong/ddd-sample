package com.ppyong.sample.global.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Profile {
    public static final String DEV = "dev";
    public static final String STG = "stg";
    public static final String PROD = "prod";
    public static final String TEST = "test";
}
