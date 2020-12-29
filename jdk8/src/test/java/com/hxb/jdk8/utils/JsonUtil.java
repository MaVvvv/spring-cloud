package com.hxb.jdk8.utils;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hxb.jdk8.testjson.ParameterizedTypeImpl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-01-16 17:27
 */
public class JsonUtil {

    /** 谷歌json实例*/
    private static Gson gson;

    static {
        /* 规定转json串时时间格式规则*/
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

    /**
     * 对象转json串
     *
     * @param obj
     * @return String
     * @author Ma_wei
     * @since 2019/1/16
     */
    public static String toJson (Object obj) {
        return gson.toJson(obj);
    }

    /**
     * json串转java对象
     *
     * @param jsonStr,clazz<T>
     * @return <T> T
     * @author Ma_wei
     * @since 2019/1/16
     */
    public static <T> T toJavaObject (String jsonStr, Class<T> clazz) throws JSONException {
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        return jsonObject.toJavaObject(clazz);
    }

    /**
     * json数组对象转化List
     *
     * @param jsonStr,clazz<T>
     * @return <T> List<T>
     * @author Ma_wei
     * @since 2019/1/18
     */
    public static <T> List<T> toObjList (String jsonStr, Class<T> clazz) {
        return JSONObject.parseArray(jsonStr,clazz);
    }

    /**
     * 动态解析json串传值转化map集合
     *
     * @param jsonStr,keys
     * @return Map<String,Object>
     * @author Ma_wei
     * @since 2019/3/5
     */
    public static Map<String,Object> toParamMap (String jsonStr, String... keys) {
        Map<String,Object> keyMap = new HashMap<>();
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        for (String key : keys) {
            keyMap.put(key,jsonObject.get(key));
        }
        return keyMap;
    }

    /**
     * 解析json串传值转化map集合
     *
     * @param jsonStr,keys
     * @return Map<String,Object>
     * @author Ma_wei
     * @since 2019/3/5
     */
    public static Map<String,Object> toParamMap (String jsonStr) {
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        return jsonObject.getInnerMap();
    }

    /**
     * 解析具有泛型类型的java类对象
     *
     * @param jsonStr
     * @param type
     * @return T
     * @author Ma_wei
     * @since 2019/10/15
     */
    public static <T> T toJavaObject(String jsonStr, Type type) {
        return gson.fromJson(jsonStr,type);
    }
}
