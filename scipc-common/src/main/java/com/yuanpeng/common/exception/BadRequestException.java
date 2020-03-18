package com.yuanpeng.common.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * @description: 错误请求异常
 * @author: YuanPeng
 * @create: 2020-02-11 16:43
 */
@Data
public class BadRequestException extends RuntimeException{
    private Integer status = BAD_REQUEST.value();

    public BadRequestException(String msg){
        super(msg);
    }

    public BadRequestException(HttpStatus status, String msg){
        super(msg);
        this.status = status.value();
    }
}
