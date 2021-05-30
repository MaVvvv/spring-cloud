package com.channelsoft.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 供应商订单号
 *
 * @author Ma_wei
 * @since 2021/2/7 10:52
 */
@Data
public class SupOrderPO {

    @ExcelProperty("电商单据号")
    private String supOrderNo;
}
