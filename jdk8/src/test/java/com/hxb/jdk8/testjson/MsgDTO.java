package com.hxb.jdk8.testjson;

/**
 * 消息DTO
 *
 * @author Ma_wei
 * @since 2020/8/13 20:57
 */
public abstract class MsgDTO<T> {

    private String msg;

    private Integer code;

    private T data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MsgDTO{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
