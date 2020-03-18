package com.yuanpeng.security.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-17 19:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnlineUser {

    private String userName;

    private String browser;

    private String ip;

    private String address;

    private String key;

    private Date loginTime;
}