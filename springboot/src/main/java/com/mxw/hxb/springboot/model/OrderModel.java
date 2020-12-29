package com.mxw.hxb.springboot.model;

import com.mxw.hxb.springboot.annotations.SyI18nField;
import lombok.Data;

/**
 * 订单数据模型
 *
 * @author Ma_wei
 * @since 2020/12/24 11:07
 */
@Data
public class OrderModel {

    private Integer id;

    @SyI18nField
    private String name;
}
