package com.mxw.hxb.springboot.test.jd;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 聚贤阁发票提交确认/驳回数据模型
 *
 * @author Ma_wei
 * @since 2020/8/7 16:19
 */
@Data
public class JxgInvoiceSubmitParam {

    /**
     * 采购平台开票通知单号
     */
    private String pcode;

    /**
     * 通知状态 1-接受 0-驳回
     */
    private Integer status;

    /**
     * 驳回原因
     */
    private String reason;

    /**
     * 纸质发票明细
     */
    private List<InvoiceSubmitItem> items;

    /**
     * 发票确认、驳回行信息数据模型
     * 
     * @author Ma_wei
     * @sice 2020/8/7
     * @see com.sunyur.transform.jd.client.jxg.param.JxgInvoiceSubmitParam
     */
    @Data
    public static class InvoiceSubmitItem {

        /**
         * 发票税务编号
         */
        private String taxInvoiceCode;

        /**
         * 发票含税金额
         */
        private BigDecimal amount;

        /**
         * 发票不含税金额
         */
        private BigDecimal nakedAmount;

        /**
         * 发票税率
         */
        private BigDecimal taxRate;

        /**
         * 发票类型
         * （0 普票,1 专票,2 电子发票）
         */
        private Integer vatType;

        /**
         * 开票类型
         * （1:标准,2:红冲）
         */
        private Integer type;

        /**
         * 电子发票下载地址
         */
        private String url;

        /**
         * 税务发票代码
         */
        private String invoiceNumber;

        /**
         * 税务开票日期
         */
        private String invoiceDate;
    }
}
