package com.channelsoft.difflock;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-06-02 16:29
 */
public class SynchronizedTest {

    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(() -> {
            phone.sms();
        },"A").start();

        new Thread(() -> {
            phone.sms();
        },"B").start();
    }
}

class Phone{

    public synchronized void sms(){
        System.out.println(Thread.currentThread().getName() + "---sms()...");
        call(); // 这里也有锁
    }

    public synchronized void call(){
        System.out.println(Thread.currentThread().getName() + "---call()...");
    }
}
