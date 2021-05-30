package com.mxw.hxb.springboot.web;

import com.mxw.hxb.springboot.service.LogInfoService;
import com.sunyur.common.pagination.ModelResult;
import com.sunyur.common.pagination.ModelResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 操作日志控制器
 *
 * @author Ma_wei
 * @since 2021/2/7 17:03
 */
@RestController
@RequestMapping(value = "/log-info")
public class LogInfoController {

    @Autowired
    private LogInfoService logInfoServiceImpl;

    @GetMapping(value = "/batchInsert")
    public ModelResult<Boolean> batchInsertLog(){
        try {
            logInfoServiceImpl.batchInsertLog();
        } catch (Exception e) {
            return ModelResultUtil.error(e.getMessage());
        }
        return ModelResultUtil.success(Boolean.TRUE);
    }
}
