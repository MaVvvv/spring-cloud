package com.channelsoft.jmm;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-06-02 14:37
 */
public class VTest {

    // 不保证原子性
    private static volatile int num = 0;

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
        num ++;
    }
}
