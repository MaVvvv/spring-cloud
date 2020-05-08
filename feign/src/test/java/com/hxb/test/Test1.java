package com.hxb.test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-04-03 17:50
 */
public class Test1 {

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        Runnable add = () -> {
            System.out.println("123");
            System.out.println("456");
        };
        add.run();
        Runnable add1 = () -> {
            System.out.println("1");
            System.out.println("1");
        };
        add1.run();
        Runnable add2 = () -> {
            System.out.println("2");
            System.out.println("2");
        };
        add2.run();
        Runnable add3 = () -> {
            System.out.println("3");
            System.out.println("3");
        };
        add3.run();
        Object obj = new Object();
        System.gc();
        Map map1 = new HashMap();
    }
}
