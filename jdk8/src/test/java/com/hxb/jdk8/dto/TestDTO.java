package com.hxb.jdk8.dto;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-03-09 16:48
 */
public class TestDTO {

    private String name;

    private int age;

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

    @Override
    public String toString() {
        return "TestDTO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
