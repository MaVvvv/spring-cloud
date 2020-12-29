package com.mxw.hxb.springboot.test.jd;

import lombok.Data;

/**
 * 发票收票人地址模型
 *
 * @author Ma_wei
 * @sice 2020/8/4
 */
@Data
public class ReceiverAddressModel {

    /**
     * 一级地址编码
     */
    private String provinceCode;

    /**
     * 一级地址名称
     */
    private String provinceName;

    /**
     * 二级地址编码
     */
    private String cityCode;

    /**
     * 二级地址名称
     */
    private String cityName;

    /**
     * 三级地址编码
     */
    private String countryCode;

    /**
     * 三级地址名称
     */
    private String countryName;

    /**
     * 四级地址编码
     */
    private String townCode;

    /**
     * 四级地址名称
     */
    private String townName;

    /**
     * 详细地址
     */
    private String address;
}
