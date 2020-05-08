package cool.mawei.service;

import cool.mawei.dto.BookDTO;

/**
 * 处理书籍信息业务逻辑接口
 *
 * @author Ma_wei
 * @since 2020-02-04 22:58
 */
public interface IBookService {

    String addBook(BookDTO bookDTO);

    String updateBook(BookDTO bookDTO) throws InterruptedException;

    String getBookInfoById(int id) throws InterruptedException;
}
