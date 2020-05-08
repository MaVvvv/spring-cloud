package cool.mawei.web.handle;

import cool.mawei.dto.BookDTO;
import cool.mawei.service.IBookService;
import cool.mawei.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-02-04 22:59
 */
@Service
public class BookHandle {

    /** 日志*/
    private static final Logger log = LoggerFactory.getLogger(BookHandle.class);

    @Autowired
    private IBookService bookServiceImpl;

    /**
     * 添加书籍
     *
     * @param jsonParam
     * @return String
     * @author Ma_wei
     * @since 2020/2/4
     */
    public String addBook(String jsonParam) {
        log.debug("进入BookHandle.addBook()方法...");
        String result = null;
        try {
            BookDTO bookDTO = JsonUtil.toJavaObject(jsonParam,BookDTO.class);
            result = bookServiceImpl.addBook(bookDTO);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            result = e.getMessage();
        }
        return result;
    }

    /**
     *
     *
     * @param
     * @return
     * @author Ma_wei
     * @since 2020/2/5
     */
    public String updateBook(String jsonParam) {
        log.debug("进入BookHandle.updateBook()方法...");
        String result = null;
        try {
            BookDTO bookDTO = JsonUtil.toJavaObject(jsonParam,BookDTO.class);
            result = bookServiceImpl.updateBook(bookDTO);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            result = e.getMessage();
        }
        return result;
    }

    /**
     * 通过书籍编号获取书籍信息
     *
     * @param
     * @return 
     * @author Ma_wei
     * @since 2020/2/5
     */
    public String getBookInfoById(int id) {
        log.debug("进入BookHandle.getBookInfoById()方法...");
        String result = null;
        try {
            result = bookServiceImpl.getBookInfoById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            result = e.getMessage();
        }
        return result;
    }
}
