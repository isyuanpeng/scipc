package com.yuanpeng.common.utils;

import com.yuanpeng.common.exception.BadRequestException;

import javax.validation.ConstraintViolationException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @description: 异常工具
 * @author: YuanPeng
 * @create: 2020-02-11 16:36
 */
public class ThrowableUtil {

    /**
     * 获取堆栈信息
     * @param throwable
     * @return
     */
    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }

    public static void throwForeignKeyException(Throwable e, String msg) {
        Throwable t = e.getCause();
        while ((t != null) && !(t instanceof ConstraintViolationException)) {
            t = t.getCause();
        }
        if (t != null) {
            throw new BadRequestException(msg);
        }
        assert false;
        throw new BadRequestException("删除失败: " + t.getMessage());
    }
}
