package com.channelsoft.easyexcel.tcm;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 中医文件
 *
 * @author Ma_wei
 * @version 1.0
 * @className TCMFileDTO.class
 * @since 2021/4/15 15:51
 */
@Data
public class TCM0530FileDTO {

    /**
     * 医生姓名
     */
    @ExcelProperty(value = "医生姓名")
    private String name;

    /**
     * 性别
     *
     * 男 - 女
     */
    @ExcelProperty(value = "病人性别")
    private String sex;

    /**
     * 生日
     */
    @ExcelProperty(value = "病人生日")
    private String birthDay;

    /**
     * 中医证型
     */
    @ExcelProperty(value = "中医证型")
    private String type;

    /**
     * 处方开单
     */
    @ExcelProperty(value = "处方开单")
    private String content;

    /**
     * 主诉
     */
    @ExcelProperty(value = "主诉")
    private String cc;

    /**
     * 西医诊断
     */
    @ExcelProperty(value = "西医诊断")
    private String wTcmDiag;

    /**
     * 中医诊断
     */
    @ExcelProperty(value = "中医诊断")
    private String tcmDiag;

    @Data
    public static class OutTcmField {

        /**
         * 年龄
         */
        @ExcelProperty(value = "年龄")
        private Integer age;

        /**
         * 中药处方
         */
        @ExcelProperty(value = "中药处方")
        private String tcmMethod;

        /**
         * 西药处方
         */
        @ExcelProperty(value = "西药处方")
        private String western;
    }
}
