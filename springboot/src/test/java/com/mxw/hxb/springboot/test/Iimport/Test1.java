package com.mxw.hxb.springboot.test.Iimport;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-09-04 15:07
 */
public class Test1 {

    /** 日志*/
    private static final Logger log = LoggerFactory.getLogger(Test1.class);

    /**
     *
     *
     * @param []
     * @return void
     * @author Ma_wei
     * @since 2019/9/4
     * {@link StackTraceElement}
     */
    @Test
    public void testStack() {
        StackTraceElement[] stackTrace = new RuntimeException().getStackTrace();
        for (StackTraceElement stackTraceElement:stackTrace) {
            log.info(stackTraceElement.getClassName());
        }
    }
}
