package cool.mawei.dto;

import lombok.Data;
import lombok.ToString;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-02-04 18:08
 */
public class BookDTO {

    /** 编号*/
    private int id;

    /** 名称*/
    private String name;

    /** 总页数*/
    private int pages;

    /** 尺寸 大 中 小*/
    private int size;

    /** 作者名*/
    private String author;

    /** 发行日期*/
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pages=" + pages +
                ", size=" + size +
                ", author='" + author + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
