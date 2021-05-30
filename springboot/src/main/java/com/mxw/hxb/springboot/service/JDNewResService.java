package com.mxw.hxb.springboot.service;

import com.mxw.hxb.springboot.po.testdb.JDNewResponse;
import com.mxw.hxb.springboot.po.testdb.JDNewSkuInfo;
import com.sunyur.common.pagination.ModelResult;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * 新京东业务逻辑处理接口
 *
 * @author Ma_wei
 * @since 2021/3/5 10:38
 */
public interface JDNewResService {

    JDNewResponse<JDNewSkuInfo> getJDSkuResult(String json);
}
