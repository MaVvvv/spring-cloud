package com.channelsoft.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-05-01 11:03
 */
public class ListTest {

    public static void main(String[] args) {
        // 并发下的arrayList是不安全的
        /*
          解决方案：Vector和synchronizedList都是通过synchronized的方式实现线程同步

          CopyOnWriteArrayList使用可重入锁ReentrantLock方式
          CopyOnWrite 写入时复制 COW 计算机程序设计领域的一种优化策略；
          多个线程调用的时候，list，读取的时候，固定的，写入（覆盖）
          在写入的时候避免覆盖，造成数据问题！
          读写分离
        // CopyOnWriteArrayList 比 Vector Nb 在哪里？
         */
        List<String> arrayList = new ArrayList<>();
        List<String> vector = new Vector<>();
        List<String> synchronizedList = Collections.synchronizedList(new ArrayList<>());
        List<String> copyOnWriteList = new CopyOnWriteArrayList<>();
    }
}
