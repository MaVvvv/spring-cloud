package com.mxw.hxb.freemarker.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.mxw.hxb.freemarker.model.ParamDefinition;
import com.networknt.schema.JsonType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Copyright(c) 2018 Sunyur.com, All Rights Reserved.
 * Project: sop_common_share
 * Author: weidong.li
 * Createdate: 2020-03-19 16:00
 */
public class JsonToParamDefinitionUtil {

    private final static Logger LOGGER_INFO = LoggerFactory.getLogger(JsonToParamDefinitionUtil.class);

    private static Pattern integer = Pattern.compile("^-?\\d+(\\d+)?$");
    private static Pattern number = Pattern.compile("^[0-9]+([.][0-9]+)?$");

    static {
        // json排序
        JSON.DEFAULT_PARSER_FEATURE = Feature.config(JSON.DEFAULT_PARSER_FEATURE, Feature.OrderedField, true);
    }

    /**
     * @param jsonStr:
     * @Description: json转参数定义
     * @author weidong.li
     * @date 2020-03-20 10:12
     * @return: com.sunyur.common.jsonschema.ParamDefinition
     */
    public static ParamDefinition convert(String jsonStr) {
        if (StringUtils.isEmpty(jsonStr)) {
            return null;
        }

        ParamDefinition paramDefinition = new ParamDefinition("root", true);
        if (JSONObject.isValidArray(jsonStr)) {
            //处理数组类型
            paramDefinition.setType(JsonType.ARRAY.toString());
            //jsonArrays排序
            JSONArray jsonArray = JSONArray.parseArray(jsonStr);
            ParamDefinition items = new ParamDefinition("items");
            items.setIsArrayItems(true);
            Object object = jsonArray.size() > 0 ? jsonArray.get(0) : "";
            if (object instanceof JSONObject) {
                items.setType(JsonType.OBJECT.toString());
                items.setSubParams(getSubParams(jsonArray.getJSONObject(0)));
            } else {
                items.setType(getType(object));
                items.setDemoValue(object.toString());
                items.setMaxLength(getMaxLength(items.getType()));
            }
            paramDefinition.setSubParams(Collections.singletonList(items));
        } else if (JSONObject.isValidObject(jsonStr)) {
            //处理对象类型
            paramDefinition.setType(JsonType.OBJECT.toString());
            JSONObject jsonObject = JSONObject.parseObject(jsonStr, Feature.OrderedField);
            paramDefinition.setSubParams(getSubParams(jsonObject));
        } else if (JSONObject.isValid(jsonStr)) {
            paramDefinition.setType(getTypeByValue(jsonStr));
            paramDefinition.setDemoValue(jsonStr);
            paramDefinition.setMaxLength(getMaxLength(paramDefinition.getType()));
        } else {
            paramDefinition.setType(JsonType.STRING.toString());
            paramDefinition.setDemoValue(jsonStr);
            paramDefinition.setMaxLength(getMaxLength(paramDefinition.getType()));
        }

        return paramDefinition;
    }

    private static List<ParamDefinition> getSubParams(JSONObject jsonObject) {
        if (Objects.isNull(jsonObject)) {
            return null;
        }

        List<ParamDefinition> paramDefinitions = new ArrayList<>();
        Set<Map.Entry<String, Object>> set = jsonObject.entrySet();
        for (Map.Entry<String, Object> entry : set) {
            String key = entry.getKey();
            Object value = entry.getValue();
            ParamDefinition paramDefinition = new ParamDefinition(key.trim());

            if (value instanceof JSONArray) {
                paramDefinition.setType(JsonType.ARRAY.toString());
                JSONArray jsonArray = (JSONArray) value;
                ParamDefinition items = new ParamDefinition("items");
                items.setIsArrayItems(true);
                Object object = jsonArray.size() > 0 ? jsonArray.get(0) : "";
                if (object instanceof JSONObject) {
                    items.setType(JsonType.OBJECT.toString());
                    items.setSubParams(getSubParams(jsonArray.getJSONObject(0)));
                } else {
                    items.setDemoValue(object.toString());
                    items.setType(getType(object));
                    items.setMaxLength(getMaxLength(items.getType()));
                }
                paramDefinition.setSubParams(Collections.singletonList(items));
            } else if (value instanceof JSONObject) {
                paramDefinition.setType(JsonType.OBJECT.toString());
                paramDefinition.setSubParams(getSubParams((JSONObject) value));
            } else {
                paramDefinition.setType(getType(value));
                if (Objects.nonNull(value)) {
                    paramDefinition.setDemoValue(value.toString());
                }
                paramDefinition.setMaxLength(getMaxLength(paramDefinition.getType()));
            }
            paramDefinitions.add(paramDefinition);
        }
        return paramDefinitions;
    }

    private static String getType(Object value) {
        if (value instanceof Boolean) {
            return JsonType.BOOLEAN.toString();
        } else if (value instanceof Integer) {
            return JsonType.INTEGER.toString();
        } else if (value instanceof BigDecimal) {
            return JsonType.NUMBER.toString();
        } else {
            return JsonType.STRING.toString();
        }
    }

    private static String getTypeByValue(String value) {
        if (Boolean.TRUE.toString().equals(value) || Boolean.FALSE.toString().equals(value)) {
            return JsonType.BOOLEAN.toString();
        } else if (integer.matcher(value).matches()) {
            return JsonType.INTEGER.toString();
        } else if (number.matcher(value).matches()) {
            return JsonType.NUMBER.toString();
        } else {
            return JsonType.STRING.toString();
        }
    }

    private static Integer getMaxLength(String type) {
        if (JsonType.STRING.toString().equals(type)) {
            return 64;
        } else if (JsonType.INTEGER.toString().equals(type)) {
            return 10;
        } else if (JsonType.NUMBER.toString().equals(type)) {
            return 10;
        } else if (JsonType.BOOLEAN.toString().equals(type)) {
            return null;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {

        String s1 = "123";
        String s2 = "asd";
        String s3 = "1.2";
        String s4 = "true";
        String s5 = "[\"123\"]";
        String s6 = "[123]";
        String s7 = "{\n" +
                "    \"a\":[\n" +
                "        \"100\"\n" +
                "    ]\n" +
                "}";

        String s = "{\n" +
                "\t\"field1\": \"\",\n" +
                "\t\"field2\": null,\n" +
                "\t\"field3\": []\n" +
                "}";

        String ss = "{\n" +
                "\t\"field1\": \"ss\",\n" +
                "\t\"field2\": 1,\n" +
                "\t\"field3\": 1.2,\n" +
                "\t\"field4\": true,\n" +
                "\t\"field5\": {\n" +
                "\t\t\"field5_1\": \"asd\"\n" +
                "\t},\n" +
                "\t\"field6\": [{\n" +
                "\t\t\"field6_1\": \"asd\"\n" +
                "\t}],\n" +
                "\t\"field7\": [1, 2, 3],\n" +
                "\t\"field8\": [1.2, 2.2],\n" +
                "\t\"field9\": [\"123\", \"21\"],\n" +
                "\t\"field10\": [true, false]\n" +
                "}";
        String sss = "{\n" +
                "\t\"string\": \"abc\",\n" +
                "\t\"integer\": 123,\n" +
                "\t\"number\": 1.2,\n" +
                "\t\"bool\": true,\n" +
                "\t\"object\": {\n" +
                "\t\t\"string\": \"def\",\n" +
                "\t\t\"integer\": 456,\n" +
                "\t\t\"number\": 3.4,\n" +
                "\t\t\"bool\": false,\n" +
                "\t\t\"object\": null,\n" +
                "\t\t\"stringArr\": null,\n" +
                "\t\t\"integerArr\": null,\n" +
                "\t\t\"numberArr\": null,\n" +
                "\t\t\"boolArr\": null,\n" +
                "\t\t\"objectArr\": null\n" +
                "\t},\n" +
                "\t\"stringArr\": [\n" +
                "\t\t\"a\",\n" +
                "\t\t\"b\",\n" +
                "\t\t\"c\"\n" +
                "\t],\n" +
                "\t\"integerArr\": [\n" +
                "\t\t1,\n" +
                "\t\t2,\n" +
                "\t\t3\n" +
                "\t],\n" +
                "\t\"numberArr\": [\n" +
                "\t\t1.1,\n" +
                "\t\t2.2\n" +
                "\t],\n" +
                "\t\"boolArr\": [\n" +
                "\t\ttrue,\n" +
                "\t\tfalse\n" +
                "\t],\n" +
                "\t\"objectArr\": [{\n" +
                "\t\t\t\"string\": \"def\",\n" +
                "\t\t\t\"integer\": 456,\n" +
                "\t\t\t\"number\": 3.4,\n" +
                "\t\t\t\"bool\": false,\n" +
                "\t\t\t\"object\": {\n" +
                "\t\t\t\t\"string\": \"def\",\n" +
                "\t\t\t\t\"integer\": 456,\n" +
                "\t\t\t\t\"number\": 3.4,\n" +
                "\t\t\t\t\"bool\": false,\n" +
                "\t\t\t\t\"object\": null,\n" +
                "\t\t\t\t\"stringArr\": null,\n" +
                "\t\t\t\t\"integerArr\": null,\n" +
                "\t\t\t\t\"numberArr\": null,\n" +
                "\t\t\t\t\"boolArr\": null,\n" +
                "\t\t\t\t\"objectArr\": null\n" +
                "\t\t\t},\n" +
                "\t\t\t\"stringArr\": null,\n" +
                "\t\t\t\"integerArr\": null,\n" +
                "\t\t\t\"numberArr\": null,\n" +
                "\t\t\t\"boolArr\": null,\n" +
                "\t\t\t\"objectArr\": null\n" +
                "\t\t}\n" +
                "\n" +
                "\t]\n" +
                "}";

//        System.out.println(ss);
//        System.out.println(JSON.toJSONString(JsonToParamDefinitionUtil.convert(s1)));
//        System.out.println(JSON.toJSONString(JsonToParamDefinitionUtil.convert(s2)));
//        System.out.println(JSON.toJSONString(JsonToParamDefinitionUtil.convert(s3)));
//        System.out.println(JSON.toJSONString(JsonToParamDefinitionUtil.convert(s4)));
//        System.out.println(JSON.toJSONString(JsonToParamDefinitionUtil.convert(s5)));
//        System.out.println(JSON.toJSONString(JsonToParamDefinitionUtil.convert(s6)));
//        System.out.println(JSON.toJSONString(JsonToParamDefinitionUtil.convert(s7)));
//        System.out.println(JSON.toJSONString(JsonToParamDefinitionUtil.convert(ss)));
        System.out.println(JSON.toJSONString(JsonToParamDefinitionUtil.convert(s)));
    }

}


