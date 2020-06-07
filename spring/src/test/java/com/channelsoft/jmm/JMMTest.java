package com.channelsoft.jmm;

import java.util.concurrent.TimeUnit;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-05-28 15:44
 */
public class JMMTest {

    private static volatile int num = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
           while (num == 0) {
               System.out.println("0");
           }
        }).start();
        TimeUnit.SECONDS.sleep(1);
        num = 1;
        System.out.println(num);
    }


}
