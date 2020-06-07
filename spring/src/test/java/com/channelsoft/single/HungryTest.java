package com.channelsoft.single;

/**
 * 饿汉式单例模式
 *
 * @author Ma_wei
 * @since 2020-05-28 16:08
 */
public class HungryTest {

    private HungryTest(){}

    private static final HungryTest HUNGRY = new HungryTest();

    public static HungryTest getInstance() {
        return HUNGRY;
    }
}
