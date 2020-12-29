package com.mxw.hxb.springboot.test.model;

import lombok.Data;

import java.util.List;

/**
 * 快递100响应结果数据模型
 *
 * @author Ma_wei
 * @since 2020/9/23 11:08
 */
@Data
public class Kd100LogisticsInfoModel {

    /**
     * 响应描述
     */
    private String message;

    /**
     * 当前快递状态
     *
     * 0在途，1揽收，2疑难，3签收，4退签，5派件，6退回，7转投
     */
    private String state;

    /**
     * 请求响应状态
     */
    private String status;

    /**
     * 快递公司编码
     */
    private String com;

    /**
     * 快递单号
     */
    private String nu;

    /**
     * 查询结果
     */
    private List<LogisticsDetailModel> data;

    /**
     * 是否成功
     */
    private Boolean result;

    /**
     * 失败码
     */
    private String returnCode;

    /**
     * 物流明细数据模型
     * 
     * @author Ma_wei
     * @sice 2020/9/23
     * @see com.sunyur.sopappmarket.service.custom.kd100.model.Kd100LogisticsInfoModel
     */
    @Data
    public static class LogisticsDetailModel {

        /**
         * 时间标准格式
         */
        private String time;

        /**
         * 内容
         */
        private String context;
    }

}
