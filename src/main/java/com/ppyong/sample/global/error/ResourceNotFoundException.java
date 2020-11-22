package com.ppyong.sample.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@AllArgsConstructor
public class ResourceNotFoundException extends RuntimeException {
    private final long resourceId;
}
