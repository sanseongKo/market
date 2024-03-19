package com.market.marketJpa.controller.advice;

import com.market.marketJpa.error.BusinessException;
import com.market.marketJpa.vo.ResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiControllerAdvice {
    private static final String BIND_EXCEPTION_CODE = "BIND_001";

    @ExceptionHandler(BusinessException.class)
    public ResponseResult<Object> businessException(BusinessException e) {

        return ResponseResult.of(e.getCode(), e.getStatus(), e.getMessage(), null);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ResponseResult<Object> bindException(BindException e) {
        ObjectError data = e.getBindingResult().getAllErrors().get(0);
        return ResponseResult.of(BIND_EXCEPTION_CODE, HttpStatus.BAD_REQUEST, data.getDefaultMessage(), null);
    }
}
