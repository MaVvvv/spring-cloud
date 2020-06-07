package com.channelsoft.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-05-28 14:38
 */
public class ForkJoinMainTest {

    private static final long START = 0L;
    private static final long END = 10_0000_0000L;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*
            sum = 500000000500000000,time = 522
            sum = 500000000500000000,time = 10350
            sum = 500000000500000000,time = 339
        */
        test1(START,END);   // 522ms
        test2(START,END);   // 10350ms
        test3(START,END);   // 339ms
    }
    
    /**
     * 普通程序员
     *
     * @param start
     * @param end
     * @author Ma_wei
     * @since 2020/5/28
     */
    private static void test1(long start,long end){
        long startTime = System.currentTimeMillis();
        long sum = 0L;
        for (long i = start; i <= end; i++) {
            sum += i;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("sum = " + sum + ",time = " + (endTime - startTime));
    }

    /**
     * 会用forkJoin的程序员
     *
     * @param start
     * @param end
     * @author Ma_wei
     * @since 2020/5/28
     */
    private static void test2(long start,long end) throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        ForkJoinPool joinPool = new ForkJoinPool();
        ForkJoinTask<Long> forkJoinTask = new ForkJoinTest(start,end);
        ForkJoinTask<Long> submit = joinPool.submit(forkJoinTask);
        long sum = submit.get();
        long endTime = System.currentTimeMillis();
        System.out.println("sum = " + sum + ",time = " + (endTime - startTime));
    }

    /**
     * 会用Stream并行流的程序员
     *
     * @param start
     * @param end
     * @author Ma_wei
     * @since 2020/5/28
     */
    private static void test3(long start,long end) {
        long startTime = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(start,end).parallel().reduce(0,Long::sum);
        long endTime = System.currentTimeMillis();
        System.out.println("sum = " + sum + ",time = " + (endTime - startTime));
    }
}
