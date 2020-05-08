package com.channelsoft.thread;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-05-01 11:49
 */
public class SetTest {

    public static void main(String[] args) {

        // 通过锁定具体是实现
        Set<String> set = Collections.synchronizedSet(new HashSet<>());
        // 通过使用可重入锁实现线程同步
        Set<String> copyOnWriteSet = new CopyOnWriteArraySet<>();
    }
}
