package com.channelsoft.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-06-04 20:47
 */
public class MapTest {

    public static void main(String[] args) {
        Map<String,String> hashMap = new HashMap<>();
        Map<String,String> map = new ConcurrentHashMap<>();
    }
}
