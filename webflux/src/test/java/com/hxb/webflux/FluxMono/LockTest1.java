package com.hxb.webflux.FluxMono;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-06-14 17:05
 */
public class LockTest1 {

    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        LockTest1 lockTest1 = new LockTest1();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                //lockTest1.method(Thread.currentThread());
                lockTest1.tryMethod(Thread.currentThread());
            }
        },"t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                //lockTest1.method(Thread.currentThread());
                lockTest1.tryMethod(Thread.currentThread());
            }
        },"t2");
        t1.start();
        t2.start();
    }

    private void method(Thread thread) {
        lock.lock();
        try {
            System.out.println("线程名"+thread.getName() + "获得了锁");
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("线程名：" + thread.getName() + "释放了锁");
            lock.unlock();
        }
    }

    private void tryMethod(Thread thread) {
        if (lock.tryLock()) {
            try {
                System.out.println("线程名"+thread.getName() + "获得了锁");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("线程名：" + thread.getName() + "释放了锁");
                lock.unlock();
            }
        } else {
            System.out.println("我是"+Thread.currentThread().getName()+"有人占着锁，我就不要啦");
        }

    }

}
