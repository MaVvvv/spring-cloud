package com.mxw.hxb.springboot.test.constant;

/**
 * 参数
 *
 * @author Ma_wei
 * @since 2020/9/30 15:34
 */
public class Param1 {

    private Integer param;

    private String sign;

    public Integer getParam() {
        return param;
    }

    public void setParam(Integer param) {
        this.param = param;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "Param1{" +
                "param=" + param +
                ", sign='" + sign + '\'' +
                '}';
    }
}
