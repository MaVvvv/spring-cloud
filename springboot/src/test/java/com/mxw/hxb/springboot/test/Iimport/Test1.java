package com.mxw.hxb.springboot.test.Iimport;

import com.alibaba.fastjson.JSONObject;
import net.minidev.json.JSONUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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

    @Test
    public void testCopy(){
        Dog dog = new Dog();
        dog.setAge(11);
        Cat cat = new Cat();
        cat.setName("miaomiao");
        cat.setHeight(10L);
        JSONObject a1 = JSONObject.parseObject(JSONObject.toJSONString(dog));
        JSONObject a2 = JSONObject.parseObject(JSONObject.toJSONString(cat));
        a1.putAll(a2.getInnerMap());

        System.out.println(JSONObject.toJSONString(a1));
    }

}
