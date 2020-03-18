package com.yuanpeng.common.base;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @description: 基础DTO
 * @author: YuanPeng
 * @create: 2020-02-11 15:59
 */
@Data
public class BaseDto implements Serializable {
    private Integer status;

    private Timestamp createTime;

    private Timestamp updateTime;

    @Override
    public String toString() {
        return "BaseDTO{" +
                "status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
