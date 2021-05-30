package com.mxw.hxb.springboot.po.testdb;

import com.alibaba.fastjson.annotation.JSONField;
import com.mxw.hxb.springboot.annotations.JDNewRes;
import lombok.Data;

/**
 * 京东统一响应参数
 *
 * @author Ma_wei
 * @since 2021/3/5 10:41
 */
public class JDNewResponse <T> {

    /**
     * 错误描述
     */
    private ErrorResponse error_response;

    private ResResultInfo<T> resResultInfo;

    /**
     * 响应结果信息
     *
     * @author Ma_wei
     * @sice 2021/3/5
     * @see
     */
    @Data
    public static class ResResultInfo <T>{

        /**
         * 响应码
         */
        private String code;

        /**
         * Rpc响应结果
         */
        private OpenRpcResult<T> openRpcResult;

        /**
         * Rpc响应结果实体类
         *
         * @author Ma_wei
         * @sice 2021/3/5
         * @see
         */
        @Data
        public static class OpenRpcResult<T> {

            /**
             * 响应码
             */
            private String resultCode;

            /**
             * 结果
             */
            private T result;

            /**
             * 错误信息描述
             */
            private String resultMessage;

            /**
             * 是否成功
             */
            private Boolean success;

        }
    }

    /**
     * 错误响应shili
     *
     * @author Ma_wei
     * @sice 2021/3/5
     * @see
     */
    @Data
    public static class ErrorResponse {

        /**
         * 响应码
         */
        private String code;

        /**
         * 中文描述
         */
        private String zh_desc;

        /**
         * 英文描述
         */
        private String en_desc;
    }

    public ErrorResponse getError_response() {
        return error_response;
    }

    public void setError_response(ErrorResponse error_response) {
        this.error_response = error_response;
    }

    public ResResultInfo<T> getResResultInfo() {
        return resResultInfo;
    }

    public void setResResultInfo(ResResultInfo<T> resResultInfo) {
        this.resResultInfo = resResultInfo;
    }
}
