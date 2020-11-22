package com.ppyong.sample.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
public class ErrorResponse {
    private String code;

    private String message;

    private List<Error> errors;

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorResponse(String code, String message, List<Error> errors) {
        this.code = code;
        this.message = message;
        this.errors = errors;
    }

    @Getter
    @AllArgsConstructor
    public static class Error {
        private String property;

        private String message;
    }
}
