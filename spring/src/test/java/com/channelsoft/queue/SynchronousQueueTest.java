package com.channelsoft.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 同步队列
 * 和其他的BlockingQueue不一样 SynchronousQueue不存储元素
 * put一个元素，必须等从里面取出来后，才能再put进去新元素
 *
 * @author Ma_wei
 * @since 2020-05-07 17:27
 */
public class SynchronousQueueTest {

    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "put A");
                blockingQueue.put("A");
                System.out.println(Thread.currentThread().getName() + "put B");
                blockingQueue.put("B");
                System.out.println(Thread.currentThread().getName() + "put C");
                blockingQueue.put("C");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T1").start();
        new Thread(() -> {
            try {
                // 模拟睡眠
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "take" + blockingQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "take" + blockingQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "take" + blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T2").start();
    }
}
