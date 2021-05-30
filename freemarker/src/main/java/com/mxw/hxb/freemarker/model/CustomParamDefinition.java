package com.mxw.hxb.freemarker.model;

import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

/**
 * Copyright(c) 2018 Sunyur.com, All Rights Reserved.
 * Project: sop_common_share
 * Author: weidong.li
 * Createdate: 2020-07-07 12:07
 */
public class CustomParamDefinition implements Serializable {

    private static final long serialVersionUID = 1953403930017575987L;

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
    private Boolean required;

    /**
     * 最大长度
     */
    private Integer maxLength;

    /**
     * 枚举值映射
     * [{
     * "syValue": "1",
     * "valueMap": [{
     * "value": "100",
     * "desc": "xxx"
     * }]* 	},
     * {
     * "syValue": "2",
     * "valueMap": [{
     * "value": "200",
     * "desc": "yyy"
     * }        , {
     * "value": "300",
     * "desc": "zzz"
     * }]
     * }
     * ]
     */
    private List<CustomParamDefinitionValueConfig> enumList;

    /**
     * 是否显示
     */
    private Boolean show = true;

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


    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<CustomParamDefinitionValueConfig> getEnumList() {
        return enumList;
    }

    public void setEnumList(List<CustomParamDefinitionValueConfig> enumList) {
        this.enumList = enumList;
    }

    public String getDescriptionExt() {
        List<CustomParamDefinitionValueConfig> list = findEnumListValueNotEmpty();
        if (CollectionUtils.isEmpty(list)) {
            return "";
        }

        List<Map<String, String>> newEnumList = list.stream().flatMap(item -> item.getValueMap().stream()).collect(toList());
        Map<String, String> valueMap = newEnumList.stream().collect(toMap(item -> item.get("value"), item -> item.get("desc")));
        return valueMap.entrySet().stream()
                .filter(item -> StringUtils.isNotBlank(item.getKey()))
                .map(item -> item.getKey() + ": " + item.getValue())
                .collect(joining(", ", " (", ")"));
    }

    public void setDescriptionExt(String descriptionExt) {
        this.descriptionExt = descriptionExt;
    }

    public List<CustomParamDefinitionValueConfig> findEnumListValueNotEmpty() {
        if (CollectionUtils.isEmpty(enumList)) {
            return null;
        }

        List<CustomParamDefinitionValueConfig> list = new ArrayList<>();
        enumList.forEach(item -> {
            String syValue = item.getSyValue();
            List<Map<String, String>> valueMapList = item.getValueMap();

            List<Map<String, String>> notEmptyList = clearEmpty(valueMapList);
            if (CollectionUtils.isNotEmpty(notEmptyList)) {
                CustomParamDefinitionValueConfig valueConfig = new CustomParamDefinitionValueConfig();
                valueConfig.setSyValue(syValue);
                valueConfig.setValueMap(notEmptyList);
                list.add(valueConfig);
            }
        });

        return CollectionUtils.isNotEmpty(list) ? list : null;
    }

    private List<Map<String, String>> clearEmpty(List<Map<String, String>> valueMapList) {
        List<Map<String, String>> list = new ArrayList<>();
        valueMapList.forEach(map -> {
            String value = map.get("value");
            if (StringUtils.isNotBlank(value)) {
                list.add(map);
            }
        });
        return list;
    }

}

@Data
class CustomParamDefinitionValueConfig implements Serializable {

    private static final long serialVersionUID = 8006210007017488698L;

    /**
     * 商越标准值
     */
    private String syValue;

    /**
     * 自定义值及描述
     */
    private List<Map<String, String>> valueMap;

}
