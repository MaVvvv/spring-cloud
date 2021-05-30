package com.mxw.hxb.springboot.po.testdb;

import lombok.Data;

/**
 * 新京东商品信息数据模型
 *
 * @author Ma_wei
 * @since 2021/3/5 10:40
 */
@Data
public class JDNewSkuInfo {

    /**
     * 名称
     */
    private String name;

    /**
     * 商品id
     */
    private String skuId;

    /**
     * 商品类型
     */
    private String type;
}
