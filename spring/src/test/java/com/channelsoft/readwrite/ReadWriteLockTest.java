package com.channelsoft.readwrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-05-07 11:44
 */
public class ReadWriteLockTest {

    public static void main(String[] args) {
        // 无锁缓存
        /*MyCache myCache = new MyCache();
        for (int i = 1; i <= 5; i++) {
            int temp = i;
            new Thread(() -> {
                myCache.put(String.valueOf(temp),"value" + String.valueOf(temp));
            },"线程" + String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            int temp = i;
            new Thread(() -> {
                myCache.get(String.valueOf(temp));
            },"线程" + String.valueOf(i)).start();
        }*/

        // 粒度更细的锁缓存
        MyCacheLock myCacheLock = new MyCacheLock();
        for (int i = 0; i <= 5; i++) {
            int temp = i;
            new Thread(() -> {
                myCacheLock.put(String.valueOf(temp),"value" + String.valueOf(temp));
            },"线程" + String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            int temp = i;
            new Thread(() -> {
                myCacheLock.get(String.valueOf(temp));
            },"线程" + String.valueOf(i)).start();
        }
    }

}

/**
 * 无锁自定义缓存
 */
class MyCache{
    private volatile Map<String,Object> map = new HashMap<>();

    public void put(String key,Object value) {
        System.out.println(Thread.currentThread().getName() + "写入" + key);
        map.put(key,value);
        System.out.println(Thread.currentThread().getName() + "写入完成");
    }

    public void get(String key) {
        System.out.println(Thread.currentThread().getName() + "读取" + key);
        Object obj = map.get(key);
        System.out.println(Thread.currentThread().getName() + "读取 "  + obj + "完成！");
    }
}

class MyCacheLock{

    private volatile Map<String,Object> map = new HashMap<>();
    /** 读写锁*/
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    // 存 写入的时候只希望一个线程去写
    public void put(String key,Object value) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "写入" + key);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName() + "写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }

    // 取 读的时候所有人都可以读
    public void get(String key) {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "读取" + key);
            Object obj = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读取 "  + obj + "完成！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}