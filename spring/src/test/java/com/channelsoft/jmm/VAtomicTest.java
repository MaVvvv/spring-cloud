package com.channelsoft.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-06-02 15:00
 */
public class VAtomicTest {

    // 原子类
    private static volatile AtomicInteger num = new AtomicInteger(0);

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int i1 = 0; i1 < 1000; i1++) {
                    add();
                }
            }).start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + "- >" + num);
    }

    private static void add() {
        num.getAndIncrement();  // AtomicInteger + 1方法 使用CAS轻量级锁
    }
}
