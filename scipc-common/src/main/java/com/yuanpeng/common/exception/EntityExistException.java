package com.yuanpeng.common.exception;

import org.springframework.util.StringUtils;

/**
 * @description: 空实体异常
 * @author: YuanPeng
 * @create: 2020-02-11 16:44
 */
public class EntityExistException extends RuntimeException{
    public EntityExistException(Class clazz, String field, String val) {
        super(EntityExistException.generateMessage(clazz.getSimpleName(), field, val));
    }

    private static String generateMessage(String entity, String field, String val) {
        return StringUtils.capitalize(entity)
                + " with " + field + " "+ val + " existed";
    }

}
