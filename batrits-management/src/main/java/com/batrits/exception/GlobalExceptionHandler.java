package com.batrits.exception;

import com.batrits.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result handleException(Exception e){
        log.error("出错了");
        e.printStackTrace();
        return Result.error("出错了,请联系管理员~");
    }

    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException de){
        log.error("出错了");
        String message = de.getMessage();
        int i = message.indexOf("Duplicate entry");
        String errMessage = message.substring(i);
        String[] split = errMessage.split(" ");
        return Result.error(split[2]+"已存在");
    }

    @ExceptionHandler
    public Result handleDataDependencyException(DataDependencyException e){
        String message = e.getMessage();
        log.error(message);
        return Result.error(message);
    }

}
