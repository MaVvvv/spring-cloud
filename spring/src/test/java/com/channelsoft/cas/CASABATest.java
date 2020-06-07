package com.channelsoft.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-06-02 16:06
 */
public class CASABATest {

    // 如果泛型是一个包装类，注意对象的引用问题
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(1,1);

    public static void main(String[] args) {
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println("a1 = " + stamp);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean result = atomicStampedReference.compareAndSet(1,3,atomicStampedReference.getStamp(),atomicStampedReference.getStamp() + 1);
            System.out.println("a2 = " + atomicStampedReference.getStamp());
            System.out.println("a2是否修改成功？" + result);
        },"a1").start();

        // 与乐观锁原理相同
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println("b1 = " + stamp);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean result = atomicStampedReference.compareAndSet(1,5,stamp,stamp + 1);
            System.out.println("b2 = " + atomicStampedReference.getStamp());
            System.out.println("b2是否修改成功？" + result);
        },"b1").start();
    }
}
