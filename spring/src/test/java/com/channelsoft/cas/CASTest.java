package com.channelsoft.cas;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS锁
 *
 * @author Ma_wei
 * @since 2020-05-28 17:51
 */
public class CASTest {

    @Test
    public void test01() {
        AtomicInteger atomicInteger = new AtomicInteger(2020);

        // 上一次更新前的值
        System.out.println("第一次更新前的值" + atomicInteger.get());
        // 上一次更新的值
        System.out.println("第一次更新的值" + atomicInteger.get());
        /*
            期望，更新
            如果我预期的值达到了，则更新 否则不更新 CAS是CPU的并发源于
         */
        int thinkInt = atomicInteger.get();
        System.out.println(atomicInteger.compareAndSet(thinkInt,thinkInt + 1));
        System.out.println(atomicInteger.get());

        // 上一次更新前的值
        System.out.println("上一次更新前的值" + atomicInteger.getAndIncrement());
        // 上一次更新的值
        System.out.println("上一次更新的值" + atomicInteger.getAndIncrement());

        System.out.println(atomicInteger.compareAndSet(thinkInt,thinkInt + 1));
        System.out.println(atomicInteger.get());
    }

    /**
     * CAS: ABA
     *
     * @param
     * @return
     * @author Ma_wei
     * @since 2020/6/2
     */
    @Test
    public void test02() {
        AtomicInteger atomicInteger = new AtomicInteger(2020);

        // 捣乱的线程 它俩都会成功
        System.out.println(atomicInteger.compareAndSet(2020,2021));     //true
        System.out.println(atomicInteger.compareAndSet(2021,2020));     //true

        // 实际更新之前的值仍然是2020
        System.out.println(atomicInteger.compareAndSet(2020,66666));    //true
    }

    @Test
    public void test03() {

    }
}
