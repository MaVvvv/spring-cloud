package com.channelsoft.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 快递鸟实时查询接口请求参数
 *
 * @author Ma_wei
 * @since 2020/11/4 15:24
 */
@Data
public class KdniaoQueryDTO {

    @JSONField(name = "EBusinessID")
    private String EBusinessID;

    @JSONField(name = "OrderCode")
    private String OrderCode;

    public String getEBusinessID() {
        return EBusinessID;
    }

    public void setEBusinessID(String EBusinessID) {
        this.EBusinessID = EBusinessID;
    }

    public String getOrderCode() {
        return OrderCode;
    }

    public void setOrderCode(String orderCode) {
        OrderCode = orderCode;
    }
}
