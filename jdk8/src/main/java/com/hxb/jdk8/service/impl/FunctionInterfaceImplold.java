package com.hxb.jdk8.service.impl;

import com.hxb.jdk8.service.IFunctionInterface;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-11-20 15:38
 */
public class FunctionInterfaceImplold implements IFunctionInterface {

    @Override
    public void accept(Object o) {
        ConcurrentHashMap<String,String> hashMap = new ConcurrentHashMap<>();
        hashMap.put("h1","h2");

    }
}
