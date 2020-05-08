package com.channelsoft.druid.dto;

import java.util.List;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-06-06 10:13
 */
public class UserDTO {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> books;

    public List<String> getBooks() {
        return books;
    }

    public void setBooks(List<String> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id='" + id + '\'' +
                ", books=" + books +
                '}';
    }
}
