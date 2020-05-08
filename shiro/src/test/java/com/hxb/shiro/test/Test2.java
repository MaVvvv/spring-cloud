package com.hxb.shiro.test;

import com.google.common.base.Joiner;
import org.junit.Assert;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-08-09 16:02
 */
public class Test2 {

    public static void main(String[] args) {
        Joiner joiner = Joiner.on(";");
        Assert.assertEquals("a;b;c",joiner.join(new String[]{"a","b","c"}));
        System.out.println(joiner.toString());
    }
}
