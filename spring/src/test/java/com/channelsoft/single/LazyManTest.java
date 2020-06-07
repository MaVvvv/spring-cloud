package com.channelsoft.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * 懒汉式单例模式
 *
 * @author Ma_wei
 * @since 2020-05-28 16:11
 */
public class LazyManTest {

    private static boolean flag = false;

    private static volatile  LazyManTest lazyMan;

    private LazyManTest() {
        synchronized (LazyManTest.class) {
            if (!flag) {
                flag = true;
            } else {
                throw new RuntimeException("请不要试图使用反射破坏");
            }
        }
    }

    /**
     * 双重检测锁模式 DCL懒汉式单例
     *
     * @return LazyManTest
     * @author Ma_wei
     * @since 2020/5/28
     */
    public static LazyManTest getInstance() {
        if (lazyMan == null) {
            synchronized (LazyManTest.class) {
                if (lazyMan == null) {
                    lazyMan = new LazyManTest();
                }
            }
        }
        return lazyMan;
    }

    /**
     * 使用反射获取DCL懒汉式单例
     *
     * @param args
     * @author Ma_wei
     * @since 2020/5/28
     */
    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Field flag = LazyManTest.class.getDeclaredField("flag");
        flag.setAccessible(true);

        Constructor<LazyManTest> lazyManTestConstructor = LazyManTest.class.getDeclaredConstructor(null);
        lazyManTestConstructor.setAccessible(false); // false null

        LazyManTest lazyMan1 = lazyManTestConstructor.newInstance();
        flag.set(lazyMan1,false);

        LazyManTest lazyMan2 = lazyManTestConstructor.newInstance();
        System.out.println(lazyMan1);
        System.out.println(lazyMan2);
    }
}
