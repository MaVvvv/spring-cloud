package com.mxw.hxb.springboot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.shade.com.google.common.reflect.TypeToken;
import com.mxw.hxb.springboot.po.testdb.JDNewResponse;
import com.mxw.hxb.springboot.po.testdb.JDNewSkuImgInfo;
import com.mxw.hxb.springboot.po.testdb.JDNewSkuInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * aaa
 *
 * @author Ma_wei
 * @since 2021/3/5 18:22
 */
@Slf4j
@Service
public class JDNewService {

    public void getSkuInfo(String method){
        log.info("method = {}",method);
        JDNewResponse<JDNewSkuInfo> response = JSONObject.parseObject(method,new TypeToken<JDNewResponse<JDNewSkuInfo>>(){}.getType());
        log.info("JDNewService .response = {}",JSONObject.toJSONString(response));
        JDNewSkuInfo result = response.getResResultInfo().getOpenRpcResult().getResult();
        log.info("result = {}",JSONObject.toJSONString(result));
    }

    public void getSkuImgInfo(String method){
        log.info("method = {}",method);
        JDNewResponse<JDNewSkuImgInfo> response = JSONObject.parseObject(method,new TypeToken<JDNewResponse<JDNewSkuImgInfo>>(){}.getType());
        log.info("JDNewService .response = {}",JSONObject.toJSONString(response));
        JDNewSkuImgInfo result = response.getResResultInfo().getOpenRpcResult().getResult();
        log.info("result = {}",JSONObject.toJSONString(result));
    }
}
