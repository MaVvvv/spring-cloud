package com.mxw.hxb.springboot.test.jd;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 京东发票概要信息响应数据模型
 *
 * @author Ma_wei
 * @since 2020/8/7 13:50
 */
@Data
public class JdInvoiceOutlineRespModel {

    /**
     * 发票号码
     */
    private String invoiceId;

    /**
     * 发票代码
     */
    private String invoiceCode;

    /**
     * 发票日期
     */
    private Date invoiceDate;

    /**
     * 发票金额（裸价）-- 专票有值
     */
    private BigDecimal invoiceNakedAmount;

    /**
     * 发票税率 -- 专票有值
     */
    private BigDecimal invoiceTaxRate;

    /**
     * 发票税额 -- 专票有值
     */
    private BigDecimal invoiceTaxAmount;

    /**
     * 价税合计
     */
    private BigDecimal invoiceAmount;

    /**
     * 发票类型（1：普票，2：专票）
     */
    private Integer invoiceType;

    /**
     * 开票状态
     */
    private Boolean success;
}
