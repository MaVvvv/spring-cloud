package com.hxb.jdk8.service;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-11-20 14:41
 */
@FunctionalInterface
public interface IFunctionInterface<T> {

    void accept(T t);

    default int sum(int x, int y) {
        return x + y;
    }
}
