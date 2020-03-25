package com.lion.nanshancloudapi.base.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author ：Lion
 * @date ：2020/3/25 6:18 下午
 * @description ：
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public String runtimeErrorResult(RuntimeException e) {
        log.error(e.getMessage());
        return e.getMessage();
    }

}
