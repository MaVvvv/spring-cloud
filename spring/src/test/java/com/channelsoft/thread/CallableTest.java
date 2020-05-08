package com.channelsoft.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-05-01 15:44
 */
public class CallableTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable myCallable = new MyCallable();

        FutureTask<String> futureTask = new FutureTask<>(myCallable);
        new Thread(futureTask,"A").start();
        new Thread(futureTask,"B").start(); // 结果会被缓存

        // 结果
        String result = futureTask.get();
    }
}

class MyCallable implements Callable<String> {

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public String call() throws Exception {
        String threadName = Thread.currentThread().getName();
        System.out.println("线程" + threadName + "执行了call方法..");
        return "success";
    }
}
