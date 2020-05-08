package cool.mawei.constant.funci;

import cool.mawei.dto.BookDTO;
import cool.mawei.dto.BookRequest;
import cool.mawei.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

/**
 * 书籍接口数据结构转化为应用结构
 *
 * @author Ma_wei
 * @since 2020-02-04 18:36
 */
public class FIBookRequestToBookDTO implements Function<BookRequest, BookDTO>  {

    private static final Logger log = LoggerFactory.getLogger(FIBookRequestToBookDTO.class);

    private int bookId;

    public FIBookRequestToBookDTO(int bookId) {
        log.info("bookId = " + bookId);
        this.bookId = bookId;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param bookRequest the function argument
     * @return the function result
     */
    @Override
    public BookDTO apply(BookRequest bookRequest) {
        BookDTO dto = new BookDTO();
        dto.setId(bookId);
        dto.setName(bookRequest.getName());
        dto.setDate(DateUtil.getCurrentDate());
        dto.setPages(bookRequest.getPages());
        dto.setSize(bookRequest.getSize());
        dto.setAuthor("书籍" + DateUtil.getCurrentTimeMillis());
        return dto;
    };
}
