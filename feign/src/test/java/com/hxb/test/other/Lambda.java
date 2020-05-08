package com.hxb.test.other;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-04-03 17:58
 */
public class Lambda implements BinaryOperator {


    @Override
    public BiFunction andThen(Function after) {
        return null;
    }

    @Override
    public Object apply(Object o, Object o2) {
        return ((String)o + (String) o2);
    }
}
