package com.hxb.shiro.test;

import com.google.common.base.Joiner;
import org.junit.Assert;
import org.junit.Test;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-08-09 16:11
 */
public class Test3 {

    @Test
    public void testStringJoiner(){
        Joiner joiner = Joiner.on(";");
        Assert.assertEquals("a;b;c",joiner.join(new String[]{"a","b","c"}));
    }

}
