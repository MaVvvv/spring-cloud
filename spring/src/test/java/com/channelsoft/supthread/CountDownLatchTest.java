package com.channelsoft.supthread;

import java.util.concurrent.CountDownLatch;

/**
 * 火箭发射器
 *
 * @author Ma_wei
 * @since 2020-05-07 10:08
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "Go out!");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println("Clear Door");
    }
}
