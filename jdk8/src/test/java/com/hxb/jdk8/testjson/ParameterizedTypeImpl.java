package com.hxb.jdk8.testjson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 参数Type转换实现类
 *
 * @author Ma_wei
 * @since 2020/8/13 20:47
 */
public class ParameterizedTypeImpl implements ParameterizedType {

    private final Class clazz;
    private final Type[] args;

    public ParameterizedTypeImpl(Class clazz, Type[] args) {
        this.clazz = clazz;
        this.args = args != null ? args : new Type[0];
    }

    @Override
    public Type[] getActualTypeArguments() {
        return args;
    }

    @Override
    public Type getRawType() {
        return clazz;
    }

    @Override
    public Type getOwnerType() {
        return null;
    }
}
