package com.mxw.xxljobclient.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * xxl-job执行
 *
 * @author Ma_wei
 * @since  2020/7/17
 */
@Slf4j
@Component
public class XxljobDemo {

    @Value("${server.port}")
    private String port;

    @XxlJob("testPrintJobHandler")
    public ReturnT<String> testPrintlnDemo(String param) throws Exception{
        log.info("start testPrintJobHandler current port = {},param = {}",port,param);
        XxlJobLogger.log("当前执行job端口port = {}，param = {}",port,param);
        return ReturnT.SUCCESS;
    }

}
