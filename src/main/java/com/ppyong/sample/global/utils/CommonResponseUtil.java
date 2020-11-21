package com.ppyong.sample.global.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CommonResponseUtil {
    public static ResponseEntity<?> create() {
        return new ResponseEntity(HttpStatus.CREATED);
    }

    public static ResponseEntity<?> ok() {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    public static <T> ResponseEntity<?> ok(T content) {
        return new ResponseEntity(content, HttpStatus.OK);
    }
}
