package com.mxw.hxb.springboot.enums;

/**
 * 新京东响应key枚举
 *
 * @author Ma_wei
 * @since 2021/3/5 14:43
 */
public enum JDNewResKeyEnum {

    SKU_IMG("jingdong_vop_goods_checkAreaLimitList_responce"),
    SKU_INFO("jingdong_vop_goods_information_responce"),
    ;

    private String key;

    JDNewResKeyEnum(String key) {
        this.key = key;
    }
}
