package com.mxw.hxb.springboot.web;

import com.mxw.hxb.springboot.config.I18nApplicationMap;
import com.mxw.hxb.springboot.config.bean.I18nSpringBeanUtils;
import com.mxw.hxb.springboot.model.MyResult;
import com.mxw.hxb.springboot.service.MyResultService;
import com.sunyur.common.pagination.ModelResult;
import com.sunyur.common.pagination.ModelResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 测试我的响应结果控制器
 *
 * @author Ma_wei
 * @since 2020/12/28 16:48
 */
@Slf4j
@RestController
@RequestMapping(value = "/my-result")
public class MyResultController {

    //@Autowired
    private I18nApplicationMap i18nApplicationMap;

    @Autowired
    private MyResultService myResultServiceImpl;

    @GetMapping("/do")
    public MyResult doMyResult(){
        I18nApplicationMap i18nBeanMap = I18nSpringBeanUtils.getBean(I18nApplicationMap.class);
        Map<String, Map<String, List<String>>> applicationMap = i18nBeanMap.getApplicationMap();
        applicationMap.forEach((appName,businesses) -> {
            log.info("appName = {}",appName);
            businesses.forEach((moduel,packageNames) -> log.info("moduel = {} packageNames = {}",moduel,packageNames));
        });
        return myResultServiceImpl.doMyResult();
    }

    @GetMapping("/do/web")
    public MyResult doWebMyResult(){
        Map<String, Map<String, List<String>>> applicationMap = i18nApplicationMap.getApplicationMap();
        applicationMap.forEach((appName,businesses) -> {
            log.info("appName = {}",appName);
            businesses.forEach((moduel,packageNames) -> log.info("moduel = {} packageNames = {}",moduel,packageNames));
        });
        return new MyResult("00000","do-web");
    }

    @GetMapping("/do/web/model-result")
    public ModelResult<Boolean> doWebModelResult(){
        return ModelResultUtil.success(Boolean.TRUE);
    }

    @GetMapping("/do/model-result")
    public ModelResult<Boolean> doModelResult(){
        return myResultServiceImpl.doModelResult();
    }
}
