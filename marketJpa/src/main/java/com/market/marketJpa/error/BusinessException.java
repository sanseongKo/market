package com.market.marketJpa.error;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class BusinessException extends RuntimeException {
    private String code;
    private HttpStatus status;
    private String message;

    public BusinessException(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.status = errorCode.getStatus();
        this.message = errorCode.getMessage();
    }
}
