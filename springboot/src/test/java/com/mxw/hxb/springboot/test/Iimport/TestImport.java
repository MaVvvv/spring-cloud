package com.mxw.hxb.springboot.test.Iimport;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-09-04 11:51
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestImport {

    /** 日志*/
    private static final Logger log = LoggerFactory.getLogger(TestImport.class);

    @Autowired
    private Cat cat;

    @Autowired
    private Dog dog;

    @Test
    public void testPrintCatName() {
        log.info(cat.getName());
        log.info(dog.getName());
    }
}
