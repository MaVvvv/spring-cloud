package com.channelsoft.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-05-01 09:54
 */
public class Ticket {

    private int number = 10;

    //private Lock lock = new ReentrantLock();

    public synchronized void sale() {
        // 加锁
        //lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() +
                        "卖出了第" + number-- + "张票，剩余：" + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放锁
            //lock.unlock();
        }
    }
}

class saleTicket{

    public static void main(String[] args) throws Exception{
        // 并发：多线程操作同一个资源类，把资源类丢入线程
        Ticket ticket = new Ticket();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
    }
}
