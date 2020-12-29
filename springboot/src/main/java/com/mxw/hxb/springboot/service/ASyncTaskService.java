package com.mxw.hxb.springboot.service;

/**
 * 异步任务业务逻辑处理接口
 *
 * @author Ma_wei
 * @since 2020/12/23 19:16
 */
public interface ASyncTaskService {

    void doWait() throws InterruptedException;
}
