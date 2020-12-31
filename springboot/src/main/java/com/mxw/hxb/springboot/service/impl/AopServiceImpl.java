package com.mxw.hxb.springboot.service.impl;

import com.mxw.hxb.springboot.annotations.LogAnnotation;
import com.mxw.hxb.springboot.service.IAopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-09-05 19:24
 */
@Slf4j
@Service
public class AopServiceImpl implements IAopService {

    @Override
    public String test(int a, int b) {
        int c = a + b;
        if (c == 2) {
            throw new RuntimeException("计算异常！");
        }
        return String.valueOf(c);
    }

    @Async
    @Override
    public void sleepTime(int i) throws InterruptedException {
        log.warn("[{}] {} AopServiceImpl.sleepTime() start -----",Thread.currentThread().getName(),i);
        Thread.sleep(3000);
        log.warn("[{}] {} AopServiceImpl.sleepTime() end -----",Thread.currentThread().getName(),i);
    }
}
