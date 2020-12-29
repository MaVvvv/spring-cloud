package com.hxb.jdk8.dto;

import java.io.Serializable;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-03-09 16:48
 */
public class TestDTO implements Serializable {

    private String name;

    private int age;

    private String skuId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    @Override
    public String toString() {
        return "TestDTO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", skuId='" + skuId + '\'' +
                '}';
    }
}
