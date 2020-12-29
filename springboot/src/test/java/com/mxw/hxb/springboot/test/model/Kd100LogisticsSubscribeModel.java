package com.mxw.hxb.springboot.test.model;

import lombok.Data;

/**
 * 快递100物流订阅返回数据模型
 *
 * @author Ma_wei
 * @since 2020/9/28 15:58
 */
@Data
public class Kd100LogisticsSubscribeModel {

    /**
     * 响应物流信息体
     */
    private Param param;

    /**
     * 响应参数体
     * 
     * @author Ma_wei
     * @sice 2020/9/28
     * @see com.sunyur.sopappmarket.service.custom.kd100.model.Kd100LogisticsSubscribeModel
     */
    @Data
    public static class Param {

        /**
         * 监控状态
         *
         * polling-监控中 shutdown-结束 abort-中止 updateall-重新推送
         */
        private String status;

        /**
         * 监控相关消息
         */
        private String message;

        /**
         * 最新的查询结果
         */
        private Kd100LogisticsInfoModel lastResult;

    }
}
