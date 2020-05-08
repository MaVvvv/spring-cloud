package com.hxb.shiro.test;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-07-12 14:57
 */
public class Test1 {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("xiaoming");
        list.add("xiaowang");
        ListIterator<String> iterator = list.listIterator();
        while (iterator.hasNext()) {
            int curIndex = iterator.previousIndex();
            System.out.println("curIndex = " + curIndex);
            String name = iterator.next();
            System.out.println("name = " + name);
            int nextIndex = iterator.nextIndex();
            System.out.println("nextIndex = " + nextIndex);
            boolean hasNext = iterator.hasNext();
            System.out.println("hasNext = " + hasNext);
        }
    }
}
