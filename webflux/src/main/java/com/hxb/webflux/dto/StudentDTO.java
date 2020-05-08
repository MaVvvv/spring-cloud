package com.hxb.webflux.dto;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-05-31 17:02
 */
public class StudentDTO {

    private String name;

    private int age;

    private String gender;

    public StudentDTO (String name,int age,String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
