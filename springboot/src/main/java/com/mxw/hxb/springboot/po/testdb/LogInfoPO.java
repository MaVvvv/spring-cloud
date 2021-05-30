package com.mxw.hxb.springboot.po.testdb;

import lombok.Data;

import java.util.Date;

/**
 * 操作日志
 *
 * @author Ma_wei
 * @since 2021/2/7 16:59
 */
@Data
public class LogInfoPO {

    private long id;

    private String param;

    private Date createTime;
}
