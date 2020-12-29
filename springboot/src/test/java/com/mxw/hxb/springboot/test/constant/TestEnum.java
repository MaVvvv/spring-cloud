package com.mxw.hxb.springboot.test.constant;

/**
 * 测试枚举
 *
 * @author Ma_wei
 * @since 2020/8/4 15:30
 */
public enum TestEnum {

    NONE("none"),
    ;

    private String val;

    TestEnum(String val) {
        this.val = val;
    }
}
