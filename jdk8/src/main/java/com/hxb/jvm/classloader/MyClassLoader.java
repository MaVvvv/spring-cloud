package com.hxb.jvm.classloader;

/**
 * 自定义的类加载器
 *
 * @author Ma_wei
 * @since 2021/2/18 14:02
 */
public class MyClassLoader extends ClassLoader{

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}
