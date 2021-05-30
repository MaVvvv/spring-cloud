package com.channelsoft.supthread;

import java.util.concurrent.CountDownLatch;

/**
 * TODO
 *
 * @author Ma_wei
 * @version 1.0
 * @className TestCountDown.class
 * @since 2021/5/13 14:26
 */
public class TestCountDown {

    public void testCountDownSync(String threadName) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        this.countDown(countDownLatch);
        countDownLatch.await();
        System.out.println(threadName + ">>>>>>>>>" + countDownLatch.getCount());
    }

    private String countDown(CountDownLatch countDownLatch) throws InterruptedException {
        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        }).start();

        return "success";
    }
}
