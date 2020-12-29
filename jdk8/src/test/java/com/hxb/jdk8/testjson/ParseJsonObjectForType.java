package com.hxb.jdk8.testjson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 解析泛型json对象
 *
 * @author Ma_wei
 * @since 2020/8/14 10:21
 */
public abstract class ParseJsonObjectForType<T> {

    public ParseJsonObjectForType() {
        Type type = getClass().getGenericSuperclass();
        Type[] types = ((ParameterizedType)type).getActualTypeArguments();
    }
}
