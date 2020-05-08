package cool.mawei.dto;

import lombok.Data;
import lombok.ToString;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-02-04 18:08
 */
@Data
@ToString
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

}
