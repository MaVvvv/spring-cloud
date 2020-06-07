package com.channelsoft;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 * 集合流式计算
 *
 * @author Ma_wei
 * @since 2020-05-28 15:16
 */
public class StreamTest {

    @Test
    public void test01() {
        List<String> list = Lists.newArrayList("a","b","c","d","e","f","g");
        list.stream().filter(ele -> !ele.equals("a")).forEach(System.out::println);
    }
}
