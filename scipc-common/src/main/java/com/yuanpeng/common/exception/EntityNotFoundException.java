package com.yuanpeng.common.exception;

import org.springframework.util.StringUtils;

/**
 * @description: 找不到实体
 * @author: YuanPeng
 * @create: 2020-02-11 16:46
 */
public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(Class clazz, String field, String val) {
        super(EntityNotFoundException.generateMessage(clazz.getSimpleName(), field, val));
    }

    private static String generateMessage(String entity, String field, String val) {
        return StringUtils.capitalize(entity)
                + " with " + field + " "+ val + " does not exist";
    }
}
