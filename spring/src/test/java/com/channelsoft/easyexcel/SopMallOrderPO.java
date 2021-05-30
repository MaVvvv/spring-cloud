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
public class SopMallOrderPO {

    @ExcelProperty("order_no")
    private String orderNo;
}
