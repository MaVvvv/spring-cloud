package com.cool.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-04-10 18:13
 */
public class BeanUtils {

    private static ApplicationContext ctx;

    public static Object getBean(String beanName) {
        Assert.notNull(ctx,"does't init Spring ApplicationContext!");
        return ctx.getBean(beanName);
    }

    public static <T> T getBean(String beanName,Class<T> clazz) {
        Assert.notNull(ctx,"does't init Spring ApplicationContext!");
        return ctx.getBean(beanName,clazz);
    }

    public static void init(ApplicationContext applicationContext) {
        ctx = applicationContext;
    }
}
