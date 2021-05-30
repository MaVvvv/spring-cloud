package com.mxw.hxb.springboot.web;

import com.aliyun.openservices.shade.com.google.common.reflect.TypeToken;
import com.mxw.hxb.springboot.annotations.JDNewRes;
import com.mxw.hxb.springboot.enums.JDNewResKeyEnum;
import com.mxw.hxb.springboot.po.testdb.JDNewResponse;
import com.mxw.hxb.springboot.po.testdb.JDNewSkuImgInfo;
import com.mxw.hxb.springboot.po.testdb.JDNewSkuInfo;
import com.mxw.hxb.springboot.service.JDNewResService;
import com.sunyur.common.pagination.ModelResult;
import com.sunyur.common.pagination.ModelResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;

/**
 * 新京东响应处理控制层
 *
 * @author Ma_wei
 * @since 2021/3/5 10:34
 */
@Slf4j
@RequestMapping(value = "/jd-new")
@RestController
public class JDNewResController {

    @Autowired
    private JDNewResService jdNewResServiceImpl;

    @PostMapping(value = "/do")
    public ModelResult<?> doHandleReq(@RequestBody String jsonParam) throws Exception {
        log.warn("param = {}",jsonParam);
        return ModelResultUtil.success(jdNewResServiceImpl.getJDSkuResult(jsonParam));
    }
}
