package com.mxw.hxb.springboot.test.jd;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 发票行明细数据模型
 * 
 * @author Ma_wei
 * @sice 2020/8/4
 */
@Data
public class InvoiceDetailItemsModel {

    /**
     * 采购平台发票行编号
     */
    private String pcode;

    /**
     * 电商商品唯一标识
     */
    private String skuId;

    /**
     * 商品名称
     */
    private String skuName;

    /**
     * 规格型号
     */
    private String skuSpec;

    /**
     * 发货单金额（含税）
     */
    private BigDecimal amount;

    /**
     * 电商平台编号
     * type = 1 发货单code,
     * type = 2 订单code
     */
    private String deliveryCode;

    /**
     * 电商平台订单编号
     */
    private String orderCode;

    /**
     * 发票行类型 (1 发货 2 运费)
     */
    private Integer type;
}
