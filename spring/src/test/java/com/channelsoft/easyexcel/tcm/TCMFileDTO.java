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
public class TCMFileDTO {

    /**
     * 医生姓名
     */
    @ExcelProperty(value = "医生姓名")
    private String name;

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

    /**
     * 性别
     *
     * 男 - 女
     */
    @ExcelProperty(value = "性别")
    private String sex;

    /**
     * 年龄
     */
    @ExcelProperty(value = "年龄")
    private Integer age;

    /**
     * 中医证型
     */
    @ExcelProperty(value = "中医证型")
    private String type;
}
