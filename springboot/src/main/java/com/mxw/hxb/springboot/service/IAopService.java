package com.mxw.hxb.springboot.service;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-09-05 19:24
 */
public interface IAopService {

    String test(int a,int b);

    void sleepTime(int i) throws InterruptedException;
}
