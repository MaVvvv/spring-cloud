package com.channelsoft.ifuninterfence;

import java.util.function.Supplier;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-05-19 18:50
 */
public class HelloWorldSupplier implements Supplier<String> {

    /**
     * Gets a result.
     *
     * @return a result
     */
    @Override
    public String get() {
        return "hello world";
    }
}
