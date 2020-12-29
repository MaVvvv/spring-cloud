package com.cool.constant;

/**
 * 服务目标类型枚举
 *
 * @author Ma_wei
 * @since  2020/7/14
 */
public enum TargetTypeEnum {

    NONE(0,"未知"),
    PURCHASE(1,"企业系统集成"),
    MALL(2,"电商对接"),
    ;

    /** 服务目标类型 target_type*/
    private Integer type;

    /** 服务目标类型描述*/
    private String desc;

    TargetTypeEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static TargetTypeEnum typeOf(Integer type){
        TargetTypeEnum targetTypeEnum;
        switch (type){
            case 1:
                targetTypeEnum = PURCHASE;
                break;
            case 2:
                targetTypeEnum = MALL;
                break;
            default:
                targetTypeEnum = NONE;
        }
        return targetTypeEnum;
    }

}
