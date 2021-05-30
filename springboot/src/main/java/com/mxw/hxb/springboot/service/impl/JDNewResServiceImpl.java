package com.mxw.hxb.springboot.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.shade.com.google.common.reflect.TypeToken;
import com.mxw.hxb.springboot.annotations.JDNewRes;
import com.mxw.hxb.springboot.enums.JDNewResKeyEnum;
import com.mxw.hxb.springboot.filter.JSONObjectKeyFilter;
import com.mxw.hxb.springboot.po.testdb.JDNewResponse;
import com.mxw.hxb.springboot.po.testdb.JDNewSkuInfo;
import com.mxw.hxb.springboot.service.JDNewResService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

/**
 * 新京东业务逻辑处理实例
 *
 * @author Ma_wei
 * @since 2021/3/5 10:57
 */
@Slf4j
@Service
public class JDNewResServiceImpl implements JDNewResService {

    @Autowired
    private JDNewService jdNewService;

    @Autowired
    private JSONObjectKeyFilter jsonObjectKeyFilter;

    @Override
    public JDNewResponse<JDNewSkuInfo> getJDSkuResult(String json) {
        log.info("JDNewResServiceImpl.getJDSkuResult");
        jdNewService.getSkuInfo(json);
        return null;
    }

}
