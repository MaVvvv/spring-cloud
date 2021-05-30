package com.mxw.hxb.springboot.service.impl;

import com.mxw.hxb.springboot.dao.testdb.LogInfoMapper;
import com.mxw.hxb.springboot.service.LogInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 操作日志业务逻辑处理接口实现类
 *
 * @author Ma_wei
 * @since 2021/2/7 17:06
 */
@Slf4j
@Service
public class LogInfoServiceImpl implements LogInfoService {

    @Autowired
    private LogInfoMapper logInfoMapper;

    private static final String PARAM_PREFIX = "this is a test param - ";

    @Override
    public void batchInsertLog() {
        for (int i = 0; i < 200_0000; i++) {
            logInfoMapper.insert(PARAM_PREFIX + i,new Date());
        }
        log.info("success");
    }
}
