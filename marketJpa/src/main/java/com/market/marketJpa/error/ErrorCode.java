package com.market.marketJpa.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    ALREADY_USER_EXIST("USER_001", "이미 유저가 있습니다", HttpStatus.BAD_REQUEST),
    NO_SUCH_ALGORITHM("INTERNAL_ERROR", "패스워드 암호화 문제", HttpStatus.SERVICE_UNAVAILABLE);

    private final String code;
    private final String message;
    private final HttpStatus status;

    ErrorCode(String code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
