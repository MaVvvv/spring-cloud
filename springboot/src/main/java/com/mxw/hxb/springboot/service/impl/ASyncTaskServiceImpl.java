package com.mxw.hxb.springboot.service.impl;

import com.mxw.hxb.springboot.service.ASyncTaskService;
import com.mxw.hxb.springboot.service.IAopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 异步任务业务逻辑处理接口实现类
 *
 * @author Ma_wei
 * @since 2020/12/23 19:16
 */
@Slf4j
@Service
public class ASyncTaskServiceImpl implements ASyncTaskService {

    @Autowired
    private IAopService aopServiceImpl;

    @Async
    @Override
    public void doWait() throws InterruptedException {
        log.warn("[{}] ASyncTaskServiceImpl.doWait() start -----",Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            log.info("cur i = {}",i);
            aopServiceImpl.sleepTime(i);
        }
        log.warn("[{}] ASyncTaskServiceImpl.doWait() end -----",Thread.currentThread().getName());
    }
}
