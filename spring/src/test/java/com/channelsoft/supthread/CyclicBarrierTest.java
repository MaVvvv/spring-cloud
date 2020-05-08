package com.channelsoft.supthread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 加法计数器
 *
 * @author Ma_wei
 * @since 2020-05-07 10:33
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,() -> {
            System.out.println("召唤神龙成功！");
        });
        for (int i = 1; i <= 7; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "收集到第" + finalI + "个龙珠！");
                try {
                    cyclicBarrier.await();
                }catch (InterruptedException|BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
