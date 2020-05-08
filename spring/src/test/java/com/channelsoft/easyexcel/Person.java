package com.channelsoft.easyexcel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

import java.util.Date;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-04-23 09:56
 */

public class Person {

    @ExcelProperty("身份证号码")
    private long cardId;

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("年龄")
    private int age;

    @ExcelProperty("出生日期")
    private Date brithday;

    @ExcelIgnore
    private String summary;

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
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

    public Date getBrithday() {
        return brithday;
    }

    public void setBrithday(Date brithday) {
        this.brithday = brithday;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "Person{" +
                "cardId=" + cardId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", brithday=" + brithday +
                '}';
    }
}
