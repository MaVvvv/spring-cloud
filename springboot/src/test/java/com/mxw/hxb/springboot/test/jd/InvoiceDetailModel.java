package com.mxw.hxb.springboot.test.jd;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 获取开票通知详情对象
 * 
 * @author Ma_wei
 * @sice 2020/8/4
 */
@Data
public class InvoiceDetailModel {

    /**
     * 采购平台开票通知编号
     */
    private String pcode;

    /**
     * 对账单行总数量
     */
    private Integer count;

    /**
     * 总页数
     */
    private Integer totalpage;

    /**
     * 页号。默认为 1，即从第 1 页开始
     */
    private Integer page;

    /**
     * 电商平台对账单编号
     */
    private String billCode;

    /**
     * 开票类型（1:标准,2:红冲）
     */
    private Integer type;

    /**
     * 发票类型
     * （0 普票， 1专票）
     */
    private Integer vatType;

    /**
     * 发票税额
     */
    private BigDecimal taxAmount;

    /**
     * 未税金额
     */
    private BigDecimal nakedAmount;

    /**
     * 发票总额
     */
    private BigDecimal amount;

    /**
     * 发票抬头
     */
    private String companyName;

    /**
     * 发票抬头电话
     */
    private String companyPhone;

    /**
     * 发票纳税人识别号
     */
    private String taxpayerCode;

    /**
     * 法人主体地址
     */
    private String companyAddress;

    /**
     * 发票开户行
     */
    private String bank;

    /**
     * 发票银行账号
     */
    private String bankAccount;

    /**
     * 开户行地址
     */
    private String bankAddress;

    /**
     * 开户行名称
     */
    private String bankName;

    /**
     * 收票人姓名
     */
    private String receiverName;

    /**
     * 收票人手机号
     */
    private String receiverMobile;

    /**
     * 收票人邮箱
     * （收电子票用）
     */
    private String receiverEmail;

    /**
     * 发票创建时间
     */
    private String createTime;

    /**
     * 收票人地址
     */
    private ReceiverAddressModel receiverAddressInfo;

    /**
     * 备注
     */
    private String remark;

    /**
     * 开票行信息
     */
    private List<InvoiceDetailItemsModel> items;
}
