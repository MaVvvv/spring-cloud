package com.hxb.jdk8.testjson;

/**
 * 111
 *
 * @author Ma_wei
 * @since 2020/8/14 10:08
 */
public class Person {

    private Long id;

    private String name;

    private Boolean age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAge() {
        return age;
    }

    public void setAge(Boolean age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
