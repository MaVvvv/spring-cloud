package com.mxw.hxb.freemarker.model;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 * Copyright(c) 2018 Sunyur.com, All Rights Reserved.
 * Project: sop_common_share
 * Author: weidong.li
 * Createdate: 2020-03-19 15:40
 */
public class ParamDefinition implements Serializable {

    private static final long serialVersionUID = -3197666407623554020L;

    public static String DEFAULT_PATTERN = "^.*$";

    /**
     * 编码 层级id
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 类型
     */
    private String type;

    /**
     * 示例
     */
    private String demoValue;

    /**
     * 描述
     */
    private String description;

    private String descriptionExt;

    /**
     * 必填
     */
    private Boolean required = true;

    /**
     * 最大长度
     */
    private Integer maxLength;

    /**
     * 是否记日志
     */
    private Boolean saveLog = true;

    /**
     * 是否显示
     */
    private Boolean show = true;

    /**
     * 正则
     */
    private String pattern;

    /**
     * 子参数
     */
    private List<ParamDefinition> subParams = new ArrayList<>();

    /**
     * 自定义
     */
    private CustomParamDefinition custom;

    /**
     * 是否根节点
     */
    private Boolean root;

    /**
     * 是否是自动添加的items节点
     */
    private Boolean isArrayItems;

    /**
     * 枚举列表
     * [
     * {
     * "value":"1"   //枚举值
     * "desc":"描素"  //枚举值描述
     * }
     * ]
     */
    private List<Map<String, String>> enumList;

    private Map<String, String> enumMap;

    public ParamDefinition() {
    }

    public ParamDefinition(String name) {
        this.name = name;
    }

    public ParamDefinition(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public ParamDefinition(String name, Boolean root) {
        this.name = name;
        this.root = root;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDemoValue() {
        return demoValue;
    }

    public void setDemoValue(String demoValue) {
        this.demoValue = demoValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public Boolean getSaveLog() {
        return saveLog;
    }

    public void setSaveLog(Boolean saveLog) {
        this.saveLog = saveLog;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public List<ParamDefinition> getSubParams() {
        return subParams;
    }

    public void setSubParams(List<ParamDefinition> subParams) {
        this.subParams = subParams;
    }

    public Boolean getRoot() {
        return root;
    }

    public void setRoot(Boolean root) {
        this.root = root;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getIsArrayItems() {
        return isArrayItems;
    }

    public void setIsArrayItems(Boolean arrayItems) {
        isArrayItems = arrayItems;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public CustomParamDefinition getCustom() {
        return custom;
    }

    public void setCustom(CustomParamDefinition custom) {
        this.custom = custom;
    }

    public String getDescriptionExt() {
        return descriptionExt;
    }

    public void setDescriptionExt(String descriptionExt) {
        this.descriptionExt = descriptionExt;
    }

    public List<Map<String, String>> getEnumList() {
        return enumList;
    }

    public void setEnumList(List<Map<String, String>> enumList) {
        this.enumList = enumList;
    }


    public Map<String, String> getEnumMap() {
        if (CollectionUtils.isEmpty(enumList)) {
            return new HashMap<>();
        }
        return enumList.stream().collect(toMap(item -> item.get("value"), item -> item.get("desc")));
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
