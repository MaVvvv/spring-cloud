package com.channelsoft.queue;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-05-07 16:46
 */
public class QueueTest {

    @Test
    public void test01() {
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        // throw java.lang.IllegalStateException: Queue full 队里满时add会抛出异常
        System.out.println(blockingQueue.add("d"));

        System.out.println("================================");

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());

        // throw java.util.NoSuchElementException 队列为空时remove会抛出异常
        //System.out.println(blockingQueue.remove());
    }

    @Test
    public void test02() {
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        // false 不会抛出异常
        System.out.println(blockingQueue.offer("d"));

        System.out.println("================================");

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());

        // null 不抛出异常
        System.out.println(blockingQueue.poll());
    }

    @Test
    public void test03() throws InterruptedException {
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        // 队列没有位置了 一直阻塞
        //blockingQueue.put("d");

        System.out.println("================================");

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        // 没有取到元素一直等待
        //System.out.println(blockingQueue.take());
    }

    @Test
    public void test04() throws InterruptedException {
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.offer("a");
        blockingQueue.offer("b");
        blockingQueue.offer("c");
        // 队列没有位置等待2s后超时
        blockingQueue.offer("d",2,TimeUnit.SECONDS);

        System.out.println("================================");

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        // 取元素等待2s后超时
        blockingQueue.poll(2, TimeUnit.SECONDS);
    }

}
