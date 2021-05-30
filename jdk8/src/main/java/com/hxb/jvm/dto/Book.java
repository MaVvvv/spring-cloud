package com.hxb.jvm.dto;

import lombok.Data;

import java.util.Date;

/**
 * 书籍
 *
 * @author Ma_wei
 * @since 2021/2/18 14:05
 */
@Data
public class Book {

    /**
     * 书籍序列号
     */
    private Long id;

    /**
     * 书籍名称
     */
    private String name;

    /**
     * 作者
     */
    private String author;

    /**
     * 发行日期
     */
    private Date releaseDate;
}
