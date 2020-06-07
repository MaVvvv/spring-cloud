package com.channelsoft.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Executors的三大方法
 *
 * @author Ma_wei
 * @since 2020-05-08 16:05
 */
public class ExecutorTest {

    public static void main(String[] args) throws InterruptedException {
        // 创建一个单个线程
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        // 创建一个固定的线程池大小
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        // 可伸缩 遇强则强 遇弱则弱
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        try {
            for (int i = 0; i < 100; i++) {
                singleThreadExecutor.execute(() -> System.out.println("[singleThreadExecutor]" + Thread.currentThread().getName() + "OK~"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            singleThreadExecutor.shutdown();
        }

        try {
            for (int i = 0; i < 100; i++) {
                fixedThreadPool.execute(() -> System.out.println("[fixedThreadPool]" + Thread.currentThread().getName() + "OK~"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fixedThreadPool.shutdown();
        }

        try {
            for (int i = 0; i < 100; i++) {
                cachedThreadPool.execute(() -> System.out.println("[cachedThreadPool]" + Thread.currentThread().getName() + "OK~"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cachedThreadPool.shutdown();
        }
    }
}
