package com.channelsoft;

import org.junit.Test;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-06-02 16:37
 */
public class Test2 {

    @Test
    public void test01() {
        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                synchronized (lock1) {
                    System.out.println("A");
                    try {
                        lock1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"a");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                synchronized (lock1) {
                    System.out.println("B");
                    try {
                        lock1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"b");
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                synchronized (lock1) {
                    System.out.println("C");
                    lock1.notifyAll();
                }
            }
        },"c");

        t1.start();
        t2.start();
        t3.start();
    }
}
