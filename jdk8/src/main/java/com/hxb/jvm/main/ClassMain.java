package com.hxb.jvm.main;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 测试类
 *
 * @author Ma_wei
 * @since 2021/2/18 14:04
 */
@Slf4j
public class ClassMain {

    public static void main(String[] args) throws Exception {
        log.info("start");
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            Book book = new Book();
            book.setId(i * 10L);
            book.setName("book" + i);
            log.debug(book.getName());
            books.add(book);
            Thread.sleep(10L);
        }
        log.info("end");
    }

    /**
     * 书籍
     *
     * @author Ma_wei
     * @since 2021/2/18 14:05
     */
    public static class Book {

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

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public Date getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(Date releaseDate) {
            this.releaseDate = releaseDate;
        }
    }
}
